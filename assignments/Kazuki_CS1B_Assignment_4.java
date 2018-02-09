//Kazuki Koshimizu CS 1B Assignment 4

/*------------------------------------------------------------------------
Two classes used by assignment #4.  Do not modify these defintions.

Place the definitions into your final submission,  with each element 
appearing above the main Foothill class, and your own classes below
Foothill. 

The imports are needed by my solution and will probably be needed
by yours, so you should place them in your project at the top of the
file(s).
----------------------------------------------------------------------- */
import java.lang.*;
import java.util.*;

// IntPair allows public, no filtering; classes that use it will protect it
class IntPair
{
   public long firstInt;
   public long secondInt;

   // constructors
   IntPair() { firstInt = secondInt = 0; }
   IntPair(long frst, long scnd) { firstInt = frst;  secondInt = scnd; }
   
   public String toString()
   {  
      return "(" + firstInt + ", " + secondInt + ")";
   }
};

//EncryptionSupport contains only static methods that clients can use wherever
//all method names should be fairly descriptive other than inverseMonN(), which
//you can take as a black-box (see description of assignment)
class EncryptionSupport
{
   static private Random randObject = new Random(System.currentTimeMillis());
   
   public static boolean isPrime(long x)
   {
      long k, loopLim;

      if (x < 2)
         return false;
      if (x < 4)
         return true;
      if (x % 2 == 0 || x % 3 == 0)
         return false;

      // now use the fact the all primes of form 6k +/- 1
      loopLim = (long)Math.sqrt(x);
      for (k = 5; k <= loopLim; k += 6)
      {
         if (x % k == 0 || x % (k + 2) == 0)
            return false;
      }
      return true;
   }
   
   public static long inverseModN(long a, long n)
   {
      // uses extended euclidean algorithm giving as + nt = gcd(n, a), 
      // with gcd(n, a) = 1,  and s, t discovered.  s = 1/a, and t ignored

      long s, t, r, sPrev, tPrev, rPrev, temp, q, inverse;

      // special key encryption conditions;  we will pick some prime e >= 3 for a
      if (a < 3 || a >= n || !isPrime(a))
         return 0;  // error

                    // we are now guaranteed 3 <= a < n and gcd(a, n) = 1;

                    // initialize working variables
      s = 0;         t = 1;         r = n;
      sPrev = 1;    tPrev = 0;    rPrev = a;

      while (r != 0)
      {
         q = rPrev / r;

         temp = r;
         r = rPrev - q * r;
         rPrev = temp;

         temp = s;
         s = sPrev - q * s;
         sPrev = temp;

         temp = t;
         t = tPrev - q * t;
         tPrev = temp;
      }

      inverse = sPrev % n;
      if (inverse < 0)
         inverse += n;
      return inverse;
   }
   
   public static long getSmallRandomPrime()
   {
      int index;
      long lowPrimes[] =
      {
               19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
               71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113,
               127, 131, 137, 139, 149, 151, 157, 163, 167, 173,
               179, 181, 191, 193, 197, 199, 211, 223, 227, 229,
               233, 239, 241, 251, 257, 263, 269, 271, 277, 281,
               283, 293, 307, 311, 313, 317, 331, 337, 347, 349,
               353, 359, 367, 373, 379, 383, 389, 397, 401, 409,
               419, 421, 431, 433, 439, 443, 449, 457, 461, 463,
               467, 479, 487, 491, 499, 503, 509, 521, 523, 541
      };
      long arraySize = lowPrimes.length;

      // pick prime in the above array bet 0 and arraySize - 1
      index = (int)( randObject.nextDouble() * arraySize );

      return lowPrimes[index]; 
   }
   
   public static long getMedSizedRandomPrime()
   {
      int index;
      long medPrimes[] =
      {
            541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607,
            613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677,
            683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761,
            769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853,
            857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937,
            941, 947, 953, 967, 971, 977, 983, 991, 997, 1009, 1013, 1019,
            1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087,
            1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151, 1153,
            1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223,
      };
      long arraySize = medPrimes.length;

      // pick prime in the above array bet 0 and arraySize - 1
      index = (int)(randObject.nextDouble() * arraySize );

      return medPrimes[index]; 
   }
}


//======================Main========================
//Kazuki Koshimizu CS 1B Assignment 4


public class Foothill
{
   public static void main(String args[])
   { 
      //==========Client Test of  Class InternetUser (first part of main())========
      InternetUser user1, user2, user3;
      user1 = new InternetUser();
      user2 = new InternetUser("Seina", "127.1.1.0");
      user3 = new InternetUser();
      user3.setName("no_ip");
            
      System.out.println("Base Class Testing ***********************\n");
      System.out.println(user1);
      System.out.println(user2);
      System.out.println(user3);
      System.out.println("Success as expected\n");
      System.out.println("END of Base Class Testing ***********************\n");
      
      
      //===========Client Test of  Class Communicator (second part of main())===========
      Communicator com1, com2, com3, com4;
      com1 = new Communicator();
      com2 = new Communicator("Ryotaro", "97.0.3.10");
      com3 = new Communicator("Nakazono", "100.10.1.0");
      com4 = new Communicator();
      com4.setName("Narita");
      
      System.out.println("Derived Class Constructor Testing ***************");
      System.out.println("------------" + "\n"
      + "Name: " + com1.getName() + "\n" + "IP Addr: " + com1.getIP() + "\n\n" 
            + com1 + "\n\n");
      
      System.out.println("------------" + "\n"
            + "Name: " + com2.getName() + "\n" + "IP Addr: " + com2.getIP() + "\n\n" 
                  + com2 + "\n\n");
      
      System.out.println("------------" + "\n"
            + "Name: " + com3.getName() + "\n" + "IP Addr: " + com3.getIP() + "\n\n" 
                  + com3 + "\n\n");
      
      System.out.println("------------" + "\n"
            + "Name: " + com4.getName() + "\n" + "IP Addr: " + com4.getIP() + "\n\n" 
                  + com4 + "\n\n");
      
      

      System.out.println("\nDerived and Base Class Mutator Testing **********");
      Communicator com5;
      com5 = new Communicator();
      com5.setName("Kotaro");
      com5.setPrimes(809, 821);
      
      System.out.println("------------" + "\n"
            + "Name: " + com5.getName() + "\n" + "IP Addr: " + com5.getIP() + "\n\n" 
                  + com5 + "\n\n");
      
      Communicator com6;
      com6 = new Communicator("Hanemoon", "1.2.3.4");
      com6.setPrimes(13, 7);
      
      System.out.println("------------" + "\n"
            + "Name: " + com6.getName() + "\n" + "IP Addr: " + com6.getIP() + "\n\n" 
                  + com6 + "\n\n");
      
      Communicator com7;
      com7 = new Communicator("not_prime_communicator", "1.2.3.4");
      com7.setPrimes(365, 100);
      
      System.out.println("------------" + "\n"
            + "Name: " + com7.getName() + "\n" + "IP Addr: " + com7.getIP() + "\n\n" 
                  + com7 + "\n\n");
      
      
      
   }
}

//base class: Internet User
class InternetUser 
{ 
   public static final String DEFAULT_IP = "0.0.0.0";
   public static final String DEFAULT_NAME = "(undefined)";
   
   public static final int MIN_NAME_LENGTH = 2;
   public static final int MAX_NAME_LENGTH = 50;
   public static final int MIN_IP_LENGTH = 7;
   public static final int MAX_IP_LENGTH = 15;
   
   private String name;
   private String ip;
    
   // Constructor with 2 parameters; name and ip
   public InternetUser(String name, String ip)
   {
      if( !setName(name) )
      {
         this.name = DEFAULT_NAME;
      }
      if( !setIP(ip) )
      {
         this.ip = DEFAULT_IP;
      }
   }
   
   // Default constructor
   public InternetUser()
   {
      name = DEFAULT_NAME;
      ip = DEFAULT_IP;
   }
   
   // Accessor
   public String getName()
   {
      return name;
   }
   
   // Mutator
   public boolean setName(String newName)
   {
      if( !isValidName(newName) )
      {
         return false;
      } 
      else 
      {
         name = newName;
         return true;
      }
   }
   
   // accessor
   public String getIP()
   {
      return ip;
   }
   
   // mutator
   public boolean setIP(String newIP)
   {
      if( !isValidIP(newIP) )
      {
         return false;
      } 
      else
      {
         ip = newIP;
         return true;
      }
   }
   
   // Test length boundaries for Name
   private boolean isValidName( String testName )
   {
      if( testName.length() < MIN_NAME_LENGTH || testName.length() > MAX_NAME_LENGTH )
      {
         return false;
      } 
      return true;
   }
   
   //return what object has
   public String toString()
   {
      String str = "Name: " + name + "\n" + "IP Addr: " + ip + "\n";
      return str;
   }
   
   // Test length boundaries for Name
   private boolean isValidIP( String testIP )
   {
      if( testIP.length() < MIN_IP_LENGTH || testIP.length() > MAX_IP_LENGTH )
      {
         return false;
      }
      return true;
   }
}


class Communicator extends InternetUser
{
   public static final int ERROR_FLAG_NUM = 0;
   public static final long MAX_PQ = (long) Math.sqrt(Long.MAX_VALUE);
   
   private IntPair publicKey;
   private IntPair privateKey;
   private long firstPrime;
   private long secondPrime;
   private long n, phi, e, d;
   
   //Constructor with 2 parameters
   
   public Communicator(long firstPrime, long secondPrime)
   {
      if( !setPrimes(firstPrime, secondPrime) )
      {
         setDefault(ERROR_FLAG_NUM);
      }
   }
   
   //constructor with 2 parameters from IP and name
   public Communicator(String name, String ip)
   {
      super(name, ip);
      setDefault(ERROR_FLAG_NUM);
   }
   
   //constructor with 4 parameters name, ip, firstPrime, secondPrime
   public Communicator(String name, String ip, long firstPrime, long secondPrime)
   {  
      super(name, ip);
      if( !setPrimes(firstPrime, secondPrime) )
      {
         setDefault(ERROR_FLAG_NUM);
      }
   }
   
   //default constructor
   public Communicator()
   {
      super();
      setDefault(ERROR_FLAG_NUM);
   }

   
   // mutator
   public boolean setPrimes(long firstPrime, long secondPrime)
   {
      if( isValidPrimes(firstPrime, secondPrime) )
      {
         this.firstPrime = firstPrime;
         this.secondPrime = secondPrime;
         computeBothEncrKeys(firstPrime, secondPrime);
         return true;
      } 
      return false;
   }
   
   private boolean computeBothEncrKeys(long p, long q)
   {
      if ( !isValidPrimes(p,q) )
      {
         return false;
      } 
         n = p * q;
         phi = (p-1)*(q-1);
         int attempts = 0;
         
         while(e > phi || e <= 0 || phi % e == 0 && attempts < 10)
         {
            e = EncryptionSupport.getSmallRandomPrime();
            attempts++;
         }
         if( e > phi || e <= 0 || phi % e == 0)
         {
            e = ERROR_FLAG_NUM;
            return false;
         }
         
         d = EncryptionSupport.inverseModN(e, n);
         publicKey = new IntPair(e, n);
         privateKey = new IntPair(d, n);
         return true;
   }
   
   // String Representation
   public String toString()
   {
      
      String output = "(p, q) n, phi, e, d: (" + firstPrime + ", " + secondPrime + ") "
            + "  " + n + ", " + phi + ", " + e + ", " + d +"\n";
      output += "public key: " + publicKey.toString() + "\n" + "private key: " + privateKey.toString() + "\n";
      return output;
   }

  
   //default value all are zero
   private void setDefault(int value)
   {
      publicKey = new IntPair();
      privateKey = new IntPair();
      phi = e = n = d = value;
      firstPrime = secondPrime = value;
   }
   
   IntPair getPublicKey()
   {
      return publicKey;
   }
   
   IntPair getPrivateKey()
   {
      return privateKey;
   }

   
   private static boolean isValidPrimes(long p, long q)
   {
      if( EncryptionSupport.isPrime(p)
            && EncryptionSupport.isPrime(q)
            && p <= MAX_PQ && q <= MAX_PQ && p != q )
      {
         return true;
      } 
      return false;
   }
}


/* ------------ Paste of run from console window --------------
Base Class Testing ***********************

Name: (undefined)
IP Addr: 0.0.0.0

Name: Seina
IP Addr: 127.1.1.0

Name: no_ip
IP Addr: 0.0.0.0

Success as expected

END of Base Class Testing ***********************

Derived Class Constructor Testing ***************
------------
Name: (undefined)
IP Addr: 0.0.0.0

(p, q) n, phi, e, d: (0, 0)   0, 0, 0, 0
public key: (0, 0)
private key: (0, 0)



------------
Name: Ryotaro
IP Addr: 97.0.3.10

(p, q) n, phi, e, d: (0, 0)   0, 0, 0, 0
public key: (0, 0)
private key: (0, 0)



------------
Name: Nakazono
IP Addr: 100.10.1.0

(p, q) n, phi, e, d: (0, 0)   0, 0, 0, 0
public key: (0, 0)
private key: (0, 0)



------------
Name: Narita
IP Addr: 0.0.0.0

(p, q) n, phi, e, d: (0, 0)   0, 0, 0, 0
public key: (0, 0)
private key: (0, 0)




Derived and Base Class Mutator Testing **********
------------
Name: Kotaro
IP Addr: 0.0.0.0

(p, q) n, phi, e, d: (809, 821)   664189, 662560, 37, 323119
public key: (37, 664189)
private key: (323119, 664189)



------------
Name: Hanemoon
IP Addr: 1.2.3.4

(p, q) n, phi, e, d: (13, 7)   91, 72, 59, 54
public key: (59, 91)
private key: (54, 91)



------------
Name: not_prime_communicator
IP Addr: 1.2.3.4

(p, q) n, phi, e, d: (0, 0)   0, 0, 0, 0
public key: (0, 0)
private key: (0, 0)




-------------------------------------------------------------- */
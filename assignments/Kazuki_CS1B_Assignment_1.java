public class Foothill 
{
   public static void main(String[] args)
   {
      System.out.println("------------phase 1------------");
      Card card1, card2, card3, card4, card5;
      
      card1 = new Card();
      card2 = new Card('9', Card.Suit.hearts);
      card3 = new Card('G', Card.Suit.diamonds);
      card4 = new Card('5');
      card5 = new Card('a', Card.Suit.spades);
      
      System.out.println(card1.toString());
      System.out.println(card2.toString());
      System.out.println(card3.toString());
      System.out.println(card4.toString());
      System.out.println(card5.toString());
      
      System.out.println("\n make 4th card bad by set()");
      card4.set('F', Card.Suit.diamonds);
      System.out.println(card4.toString());
      
      System.out.println("\n turn bad 3rd card good");
      card3.set('t', Card.Suit.spades);
      System.out.println(card3.toString());
           
      System.out.println("\n ------------phase 2------------");
      
      Hand player1;
      player1 = new Hand();
      
      int i = 0;
      while ( i < 10)
      {
         player1.takeCard(card4);
         player1.takeCard(card2);
         player1.takeCard(card3);
         i++;
      }   
     
      System.out.println("before playing\nHand = " + player1.toString());        
      
      System.out.println("\n inspecting card: 32");
      System.out.println(player1.inspectCard(32));
            
      System.out.println("\n inspecting card: 29");
      System.out.println(player1.inspectCard(29));
           
      System.out.println("\nPlaying 10 times");
            
      int k = 0;
      while ( k < 30) 
      {
         System.out.println("Playing... " + player1.playCard());
         k++;
      }
      
      System.out.println("after playing\nHand = " + player1.toString());
   }
}
      
class Card
{      
   //internal definition
   public enum Suit
   {
      clubs, diamonds, hearts, spades
   };
   
   // private member data
   private char value;
   private Suit suit;
   private boolean errorFlag;

   // 4 overloaded constructors
   public Card( char value, Suit suit)
   {
      set(value, suit);
   }
   public Card(char value)
   {
      this(value, Suit.spades);
   }

   //default constructor
   public Card()
   {
      this('A', Suit.spades);
   }

   // copy constructor
   public Card(Card card)
   {
      this.suit = card.suit;
      this.value = card.value;
   }

   // mutator
   public boolean set(char value, Suit suit)
   {
      char upVal; // for upcasing char
      boolean valid = true; // return value

      // suit is enum, so no test needed: all suits allowed
      this.suit = suit;
      
      // test val:  convert to uppercase to simplify
      upVal = Character.toUpperCase(value);
      // check for validity
      if (Card.isValild(upVal, suit))
      {
         this.value = upVal;
         this.errorFlag = false;
      }
         
      else
      {
         valid = false;
         this.value = 'A';
         this.errorFlag = true;
      }
      return valid;
   }

   // accessors
   public char getVal()
   {
      return value;
   }

   public Suit getSuit()
   {
      return suit;
   }
   
   public boolean getErrorFlag()
   {
      return errorFlag;
   }

   // stringizer
   public String toString()
   {
      String retVal;
      if (errorFlag == true)
         retVal = "[ invalid ]";
      else
         retVal = String.valueOf(value) + " of " + suit;
      return retVal;
   }
   
   //checking identical or not
   public boolean equals(Card card)
   {
      if ((this.value == card.value) && (this.suit == card.suit))
         return false;
      else
         return true;
   }
   
   private static boolean isValild(char value, Suit suit)
   {
      if(value == 'A' || value == 'K' || value == 'Q' || value == 'J'
            || value == 'T' || (value >= '2' && value <= '9'))
         return true;
      else
         return false;
   }
}

class Hand
{
   //max # of cards
   public static final int MAX_CARDS = 30;
   
   //private member
   private Card[] myCards;
   int numCards;
   
   public Hand()
   {
      myCards = new Card[MAX_CARDS];
      numCards = 0;
   }
   
   public void resetHand()
   {
       myCards = null;
       numCards = 0;
   }
   
   public boolean takeCard(Card card)
   {
      this.myCards[numCards] = new Card(card);
      numCards = numCards + 1;
      if (numCards == MAX_CARDS)
         return false;
      else
         return true;
   }
   
   public Card playCard()
   {
      Card retCard;
      retCard = new Card();
      retCard = this.myCards[numCards - 1];
      numCards = numCards - 1;
      
      return retCard;
   
   }
   
   public String toString()
   {
      String retHand = "";
      for (int i = 0; i < numCards; i++)
         retHand = retHand + "\n" + 
      this.myCards[i];
      return retHand;
   }
   
   
   public int getNumCards()
   {
      return numCards;
   }
   
   public Card inspectCard(int k)
   {
      if ( k >= 0 && k <= MAX_CARDS)
         return this.myCards[k];
      else
      {
         Card badCard;
         badCard = new Card('G', Card.Suit.spades);
         return badCard;
      }  
   }  
}


/* ------------ Paste of run from console window --------------

------------phase 1------------
A of spades
9 of hearts
[ invalid ]
5 of spades
A of spades

 make 4th card bad by set()
[ invalid ]

 turn bad 3rd card good
T of spades

 ------------phase 2------------
before playing
Hand = 
A of diamonds
9 of hearts
T of spades
A of diamonds
9 of hearts
T of spades
A of diamonds
9 of hearts
T of spades
A of diamonds
9 of hearts
T of spades
A of diamonds
9 of hearts
T of spades
A of diamonds
9 of hearts
T of spades
A of diamonds
9 of hearts
T of spades
A of diamonds
9 of hearts
T of spades
A of diamonds
9 of hearts
T of spades
A of diamonds
9 of hearts
T of spades

 inspecting card: 32
[ invalid ]

 inspecting card: 29
T of spades

Playing 10 times
Playing... T of spades
Playing... 9 of hearts
Playing... A of diamonds
Playing... T of spades
Playing... 9 of hearts
Playing... A of diamonds
Playing... T of spades
Playing... 9 of hearts
Playing... A of diamonds
Playing... T of spades
Playing... 9 of hearts
Playing... A of diamonds
Playing... T of spades
Playing... 9 of hearts
Playing... A of diamonds
Playing... T of spades
Playing... 9 of hearts
Playing... A of diamonds
Playing... T of spades
Playing... 9 of hearts
Playing... A of diamonds
Playing... T of spades
Playing... 9 of hearts
Playing... A of diamonds
Playing... T of spades
Playing... 9 of hearts
Playing... A of diamonds
Playing... T of spades
Playing... 9 of hearts
Playing... A of diamonds
after playing
Hand = 


-------------------------------------------------------------- */
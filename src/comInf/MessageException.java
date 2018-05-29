package comInf;

/**
 * This class defines an exception that is called if a message is not valid
 */

public class MessageException extends Exception
{
  /**
   *  Message that generated the exception
   *    @serialField msg
   */

   private Message msg;

  /**
   *  Create message instantiation 
   *    @param errorMessage text signalizing error
   *    @param msg message that generated the exception
   */

   public MessageException (String errorMessage, Message msg)
   {
     super (errorMessage);
     this.msg = msg;
   }

  /**
   *  Message that generated the exception getter
   *
   *    @return message
   */

   public Message getMessageVal ()
   {
     return (msg);
   }
}

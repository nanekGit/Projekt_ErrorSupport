package pl.edu.wszib.support.session;

import org.junit.Assert;
import org.junit.Test;

public class SessionObjectTest {

   @Test
   public void getInfoTest(){
       SessionObject sessionObject = new SessionObject();
       String info="ABC";

       Assert.assertNull(sessionObject.getInfo());
       sessionObject.setInfo(info);
       Assert.assertEquals(info, sessionObject.getInfo());
       Assert.assertNull(sessionObject.getInfo());
   }
}

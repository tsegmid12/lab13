package AndrewWebServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


import org.junit.Before;
import org.junit.Test;

public class AndrewWebServicesTest {
    InMemoryDatabase fakeDatabase;     
    StubRecSys stubRecSys;             
    PromoService promoServiceMock;
    AndrewWebServices andrewWebService;

    @Before
    public void setUp() {
        fakeDatabase = new InMemoryDatabase();
        fakeDatabase.addUser("Scotty", 17214);
        
        stubRecSys = new StubRecSys();
        
        promoServiceMock = mock(PromoService.class);
        andrewWebService = new AndrewWebServices(fakeDatabase, stubRecSys, promoServiceMock);
    }

    @Test
    public void testLogIn() {
        // fakeDatabase ашиглан хурдан нэвтрэлтийг шалгана.
        assertTrue("Нэвтрэлт амжилттай байх ёстой", andrewWebService.logIn("Scotty", 17214));
    }

    @Test
    public void testGetRecommendation() {
        String movie = andrewWebService.getRecommendation("Scotty");
        assertEquals("Test Movie", movie);
    }

    @Test
    public void testSendEmail() {
        String testEmail = "test@example.com";
        andrewWebService.sendPromoEmail(testEmail);

        verify(promoServiceMock).mailTo(testEmail);
    }

    @Test
    public void testNoSendEmail() {
        verify(promoServiceMock, never()).mailTo(anyString());
    }
}
/*
 * Copyright (C) 2012 Stefan Hoth, Sebastian Mauer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package st.geekli.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import st.geekli.api.GeeklistApi;
import st.geekli.api.GeeklistApiException;
import st.geekli.api.type.Activity;
import st.geekli.api.type.Card;
import st.geekli.api.type.Micro;
import st.geekli.api.type.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class ApiTest {

    //TODO move me to property files

	// Enter your API credentials here.
	private static String CONSUMER_KEY;
	private static String CONSUMER_SECRET;
	private static String TOKEN;
	private static String TOKEN_SECRET;

	// Objects used for testing
	private static String GKLST_HANDLE_YOU;
	private static String GKLST_HANDLE_SOMEONE_ELSE;
	private static String FOLLOW_USER;
	private static String CARD_ID;
	private static String HIGHFIVE_ITEM;
		
	private static GeeklistApi client;
    
    private static final String PROPS_FILE_API = "geeklist-api.properties"; 
    private static final String PROPS_FILE_TEST = "geeklist-api-test.properties"; 

    public static void setup() {

        Properties props_api = loadProperties(PROPS_FILE_API);

        if(props_api == null){
            return;
        }

        CONSUMER_KEY = (String) props_api.get("CONSUMER_KEY");
        CONSUMER_SECRET = (String) props_api.get("CONSUMER_SECRET");
        TOKEN = (String) props_api.get("TOKEN");
        TOKEN_SECRET = (String) props_api.get("TOKEN_SECRET");

        Properties props_test = loadProperties(PROPS_FILE_TEST);

        if(props_test == null){
              return;
        }

        GKLST_HANDLE_YOU = (String) props_test.get("GKLST_HANDLE_YOU");
        GKLST_HANDLE_SOMEONE_ELSE = (String) props_test.get("GKLST_HANDLE_SOMEONE_ELSE");
        FOLLOW_USER = (String) props_test.get("FOLLOW_USER");
        CARD_ID = (String) props_test.get("CARD_ID");
        HIGHFIVE_ITEM = (String) props_test.get("HIGHFIVE_ITEM");

    }

    /**
     * handle loading of property files
     * @param filename name of the properties file to load
     * @return
     */
    private static Properties loadProperties(String filename)  {

        FileInputStream fis;
        try {
            fis = new FileInputStream(filename);
            Properties props = new Properties();
            props.load(fis);

            return props;

        } catch (FileNotFoundException e) {
            GeeklistApi.debugOut("Properties file missing",PROPS_FILE_API);
            return null;
        } catch (IOException e) {
            GeeklistApi.debugOut("IOException while reading properties file '"+filename+"'",e.getMessage());
            return null;
        }

    }

    @BeforeClass public static void beforeClass()
	{
        setup();
        client = new GeeklistApi(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET, false);
	}
	
	@Test public void testGetUser()
	{
		try {
			User myself = client.getUser();
			assertNotNull("User should not be null...are you using valid OAuth credentials?", myself);
			assertEquals("API returned wrong username (check EXPECTED USER)",myself.getScreenName(), GKLST_HANDLE_YOU);
		} catch (GeeklistApiException e) {
			fail("getUser(myself) failed!");
		}
	}
	
	@Test public void testGetOtherUser()
	{
		try {
			User otherUser = client.getUser(GKLST_HANDLE_SOMEONE_ELSE);
			assertNotNull("User should not be null...does GKLST_HANDLE_SOMEONE_ELSE exist?",otherUser);
			assertEquals("API returned wrong username (check GKLST_HANDLE_SOMEONE_ELSE)", otherUser.getScreenName(), GKLST_HANDLE_SOMEONE_ELSE);
		} catch (GeeklistApiException e) {
			fail("getUser(username) failed!");
		}
	}
	
	@Test public void testGetCards()
	{
		try {
			Card[] cards = client.getCards();
			assertNotNull("getCards() should not return null. We want an empty array?", cards);
			
			if(cards.length > 0)
			{
				assertNotNull("id can't be null!", cards[0].getId());
			}
			
		} catch (GeeklistApiException e) {
			fail("getCards() failed! -> " +e);
		}
	}
	
	@Test public void testGetOtherCards()
	{
		try {
			Card[] cards = client.getCards(GKLST_HANDLE_SOMEONE_ELSE);
			assertNotNull("getCards(username) should not return null. We want an empty array?", cards);
			
			if(cards.length > 0)
			{
				assertNotNull("id can't be null!", cards[0].getId());
			}
			
		} catch (GeeklistApiException e) {
			fail("getCards(username) failed!");
		}
	}
	
	@Test public void testGetSpecificCard()
	{
		try {
			Card card = client.getCard(CARD_ID);
			assertNotNull("getCard(id) should not return null. Does your CARD_ID exist?", card);
			assertNotNull("id can't be null!", card.getId());
			
		} catch (GeeklistApiException e) {
			fail("getCard(id) failed!");
		}
	}
	
	@Test public void testCreateCard()
	{
		try {
			Card newCard = client.createCard("JUnit Testcard - "+(int)(Math.random()*100));
			assertNotNull("Creating a card failed!", newCard);
			assertNotNull("id of new card can't be null!", newCard.getId());
		} catch (GeeklistApiException e) {
			fail("createCard(headline) failed! -> "+e);
		}
	}
	
	@Test public void testGetMicros()
	{
		try {
			Micro[] micros = client.getMicros();
			assertNotNull("getMicros() should not return null. We want an empty array?", micros);
			
			if(micros.length > 0)
			{
				assertNotNull("id can't be null!", micros[0].getId());
			}
			
		} catch (GeeklistApiException e) {
			fail("getMicros() failed! -> " + e);
		}
	}
	
	@Test public void testGetOtherMicros()
	{
		try {
			Micro[] micros = client.getMicros(GKLST_HANDLE_SOMEONE_ELSE);
			assertNotNull("getMicros(username) should not return null. We want an empty array?", micros);
			
			if(micros.length > 0)
			{
				assertNotNull("id can't be null!", micros[0].getId());
			}
			
		} catch (GeeklistApiException e) {
			fail("getMicros(username) failed! -> " + e);
		}
	}
	
	@Test public void testCreateMicro()
	{
		try {
			Micro micro = client.createMicro("JUnit Micro - "+(int)(Math.random()*100));
			assertNotNull("Creating a micro failed!", micro);
			assertNotNull("id of new micro can't be null!", micro.getId());
		} catch (GeeklistApiException e) {
			fail("createMicro(status) failed! -> " + e);
		}
	}
	
	@Test public void testGetFollowers()
	{
		try {
			User[] followers = client.getFollowers();
			
			assertNotNull("getFollowers() should not return null. We want an empty array?", followers);
			
			if(followers.length > 0)
			{
				assertNotNull("id can't be null!", followers[0].getId());
			}
		} catch (GeeklistApiException e) {
			fail("getFollowers() failed! -> " + e);
		}
	}
	
	@Test public void testGetOtherFollowers()
	{
		try {
			User[] followers = client.getFollowers(GKLST_HANDLE_SOMEONE_ELSE);
			
			assertNotNull("getFollowers(username) should not return null. We want an empty array?", followers);
			
			if(followers.length > 0)
			{
				assertNotNull("id can't be null!", followers[0].getId());
			}
		} catch (GeeklistApiException e) {
			fail("getFollowers(username) failed! -> " +e);
		}
	}
	
	@Test public void testGetFollowing()
	{
		try {
			User[] following = client.getFollowing();
			
			assertNotNull("getFollowing() should not return null. We want an empty array?", following);
			
			if(following.length > 0)
			{
				assertNotNull("id can't be null!", following[0].getId());
			}
		} catch (GeeklistApiException e) {
			fail("getFollowing() failed! -> " +e);
		}
	}
	
	@Test public void testGetOtherFollowing()
	{
		try {
			User[] following = client.getFollowing(GKLST_HANDLE_SOMEONE_ELSE);
			
			assertNotNull("getFollowing(username) should not return null. We want an empty array?", following);
			
			if(following.length > 0)
			{
				assertNotNull("id can't be null!", following[0].getId());
			}
		} catch (GeeklistApiException e) {
			fail("getFollowing(username) failed! -> " +e);
		}
	}

    @Test public void testUnFollow()
    {
        try {
            client.unfollow(FOLLOW_USER);
        } catch (GeeklistApiException e) {
            fail("unfollow(username) failed! -> "+ e);
        }
    }

	@Test public void testFollow()
	{
		try {
			client.follow(FOLLOW_USER);
		} catch (GeeklistApiException e) {
			fail("follow(username) failed! -> " +e);
		}
	}

	@Test public void testGetActivity()
	{
		try {
			Activity[] activities = client.getActivity();
			
			assertNotNull("getActivity() should not return null. We want an empty array?", activities);
			
			if(activities.length > 0)
			{
				assertNotNull("id can't be null!", activities[0].getId());
			}
		} catch (GeeklistApiException e) {
			fail("getActivity() failed! -> " +e);
		}
	}
	
	@Test public void testGetOtherActivity()
	{
		try {
			Activity[] activities = client.getActivity(GKLST_HANDLE_SOMEONE_ELSE);
			
			assertNotNull("getActivity(username) should not return null. We want an empty array?", activities);
			
			if(activities.length > 0)
			{
				assertNotNull("id can't be null!", activities[0].getId());
			}
		} catch (GeeklistApiException e) {
			fail("getActivity(username) failed! -> " +e);
		}
	}
	
	@Test public void testGetAllActivity()
	{
		try {
			Activity[] activities = client.getAllActivity();

			assertNotNull("getActivity(username) should not return null. We want an empty array?", activities);

            GeeklistApi.debugOut("Activities found",""+activities.length);

			if(activities.length > 0)
			{
				assertNotNull("id can't be null!", activities[0].getId());
                GeeklistApi.debugOut("Activity#0",activities[0].toString());
			}
		} catch (GeeklistApiException e) {
			fail("getActivity(username) failed! -> "+e);
		}
	}
	
	@Test @Ignore public void testHighfive()
	{
		try {
			client.highfive("card", HIGHFIVE_ITEM);
		} catch (GeeklistApiException e) {
			fail("highfive(type, id) failed! -> "+e);
		}
	}
	
	@AfterClass public static void afterClass()
	{
		client = null;
	}
}

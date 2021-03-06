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

package st.geekli.api.type;

public class Social implements GeeklistType {

	private String twitterScreenName;
	private Integer twitterFriendsCount, twitterFollowersCount;
	
	public String getTwitterScreenName() {
		return twitterScreenName;
	}
	
	public void setTwitterScreenName(String twitterScreenName) {
		this.twitterScreenName = twitterScreenName;
	}
	
	public int getTwitterFriendsCount() {
		return twitterFriendsCount;
	}
	
	public void setTwitterFriendsCount(int twitterFriendsCount) {
		this.twitterFriendsCount = twitterFriendsCount;
	}
	
	public int getTwitterFollowersCount() {
		return twitterFollowersCount;
	}
	
	public void setTwitterFollowersCount(int twitterFollowersCount) {
		this.twitterFollowersCount = twitterFollowersCount;
	}
}

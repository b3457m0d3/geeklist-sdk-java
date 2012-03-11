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

import java.util.Date;

public abstract class Activity implements GeeklistType {

    public enum TYPE{SIGNUP, CARD, MICRO, COMMIT, HIGHFIVE };
    
	private User user;
	private TYPE type;
    private String id, content;
	private Date createdAt, updatedAt;
	
	public User getUser() {
		return user;
	}
	
	private void setUser(User user) {
		this.user = user;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	private void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	private void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public abstract TYPE getType();

    public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

    public String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Activity");
        sb.append("{user=").append(user);
        sb.append(", type='").append(type).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}

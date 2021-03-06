package st.geekli.api.type;

/**
 * represents a signup in the activity feed
 *
 * @author: Stefan Hoth <sh@jnamic.com>
 * @date: 11.03.12 13:47
 * @since: 1.0
 */
public class SignupActivity extends Activity{

    @Override
    public TYPE getType() {
        return TYPE.SIGNUP;
    }

    @Override
    public String getContent() {
        return "just signed up to Geeklist. Welcome!";
    }
}

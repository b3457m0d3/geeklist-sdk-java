package st.geekli.api.type;

/**
 * represents a highfive in the activity feed
 *
 * @author: Stefan Hoth <sh@jnamic.com>
 * @date: 11.03.12 13:47
 * @since: 1.0
 */
public class HighfiveActivity extends Activity{

    @Override
    public TYPE getType() {
        return TYPE.HIGHFIVE;
    }

    @Override
    public String getContent() {
        return "I'm a ^5 (yet to be based on real data)";
    }
}

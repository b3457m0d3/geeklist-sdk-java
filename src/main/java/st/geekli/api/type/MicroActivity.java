package st.geekli.api.type;

/**
 * represents a micro in the activity feed
 *
 * @author: Stefan Hoth <sh@jnamic.com>
 * @date: 11.03.12 13:47
 * @since: 1.0
 */
public class MicroActivity extends Activity{

    @Override
    public TYPE getType() {
        return TYPE.MICRO;
    }

    @Override
    public String getContent() {
        return "I'm a micro (yet to be based on real data)";
    }
}

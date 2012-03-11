package st.geekli.api.type;

/**
 * represents a card in the activity feed
 *
 * @author: Stefan Hoth <sh@jnamic.com>
 * @date: 11.03.12 13:47
 * @since: 1.0
 */
public class CardActivity extends Activity{

    @Override
    public TYPE getType() {
        return TYPE.CARD;
    }
}

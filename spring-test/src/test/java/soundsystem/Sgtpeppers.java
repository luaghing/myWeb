package soundsystem;

import org.springframework.stereotype.Component;

/**
 * @author
 */
@Component
public class Sgtpeppers implements CompactDisc{
    private String title ="Sgt.pepper`s Lonely Hearts Club Band";
    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println("playing "+title+" by "+artist);
    }
}

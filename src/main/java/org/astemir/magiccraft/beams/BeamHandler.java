package org.astemir.magiccraft.beams;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class BeamHandler {

    private CopyOnWriteArrayList<Beam> beams = new CopyOnWriteArrayList<>();

    public void addBeam(Beam beam){
        this.beams.add(beam);
    }


    public void update(long ticks){
        beams.forEach((beam)->{
            if (beam.isDisabled()){
                beams.remove(beam);
            }else{
                beam.update(ticks);
            }
        });
    }
}

package tagebuch;

import java.util.ArrayList;

import logik.Volk;
import vorgaenge.Arbeitsvorgang;
import vorgaenge.Behandlung;
import vorgaenge.Beobachtung;
import vorgaenge.Ernte;


/**
 * Created by maxionderon on 23.11.17.
 */

public class Tagebuch {

    //Attribute
    private ArrayList<TagebuchListenElement> tagebuchListe = new ArrayList<TagebuchListenElement>();

    //get und set Methoden
    public ArrayList<TagebuchListenElement> getTagebuchListe() {
        return tagebuchListe;
    }

    public void setTagebuchListe(ArrayList<TagebuchListenElement> tagebuchListe) {
        this.tagebuchListe = tagebuchListe;
    }

    //methode zum LiElement einfügen in tagebuch Liste
    public void addTagebuchListenElement(TagebuchListenElement tagebuchListenElement){

        this.tagebuchListe.add(tagebuchListenElement);

    }

    public void createTagebuchListeFromVolkListe(ArrayList<logik.Volk> list, logik.Volk volk ){

        for(int i = 0 ; i != list.size(); i = i + 1) {

            if(list.get(i).getVolkId() == volk.getVolkId()) {

                TagebuchVolk tagebuchVolk = new TagebuchVolk(list.get(i));

                this.tagebuchListe.add(tagebuchVolk);

            }

        }

    }

    public void createTagebuchListeFromBehandlungListe(ArrayList<Behandlung> list, logik.Volk volk ){

        for(int i = 0 ; i != list.size(); i = i + 1) {

            if(list.get(i).getVolkId() == volk.getVolkId()) {

                TagebuchBehandlung tagebuchBehandlung = new TagebuchBehandlung(list.get(i));

                this.tagebuchListe.add(tagebuchBehandlung);

            }


        }

    }

    public void createTagebuchListeFromArbeitsvorgangListe(ArrayList<Arbeitsvorgang> list, logik.Volk volk) {

        for(int i = 0 ; i != list.size(); i = i + 1) {

            if(list.get(i).getVolkId() == volk.getVolkId()) {

                TagebuchArbeitsvorgang tagebuchArbeitsvorgang = new TagebuchArbeitsvorgang(list.get(i));

                this.tagebuchListe.add(tagebuchArbeitsvorgang);

            }


        }

    }

    public void createTagebuchListeFromBeobachtung(ArrayList<Beobachtung> list, logik.Volk volk){

        for(int i = 0 ; i != list.size(); i = i + 1) {

            if(list.get(i).getVolkId() == volk.getVolkId()) {

                TagebuchBeobachtung tagebuchBeobachtung = new TagebuchBeobachtung(list.get(i));

                this.tagebuchListe.add(tagebuchBeobachtung);

            }


        }

    }

    public void createTagebuchListFromErnte(ArrayList<Ernte> list, logik.Volk volk) {

        for( int i = 0 ; i != list.size() ; i = i + 1) {

            if(list.get(i).getVolkId() == volk.getVolkId() ) {

                TagebuchErnte tagebuchErnte = new TagebuchErnte(list.get(i));

                this.tagebuchListe.add(tagebuchErnte);

            }

        }

    }

    public void clearTagebuchListe() {

        this.tagebuchListe.clear();

    }

}

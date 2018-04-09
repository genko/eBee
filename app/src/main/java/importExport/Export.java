package importExport;

import android.net.Uri;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

import datenbank.Datenbank;
import datenbank.TabelleArbeitsvorgang;
import logik.Einstellungen;
import logik.Volk;
import inventar.Inventar;
import vorgaenge.Arbeitsvorgang;
import vorgaenge.Behandlung;
import vorgaenge.Beobachtung;
import vorgaenge.Ernte;
import vorgaenge.TeilBeobachtung;

/**
 * Created by D on 13.12.2017.
 */

public class Export {

    //Atribute
    private ArrayList<Volk> volkliste;
    private Inventar inventar;
    private ArrayList<Behandlung> behandlungsListe;
    private ArrayList<Beobachtung> beobachtungsListe;
    private ArrayList<Ernte> ernteListe;
    private ArrayList<Arbeitsvorgang> arbeitsvorgaengeListe;
    private Einstellungen einstellungen;

    public Export(ArrayList<Volk> volkliste, Inventar inventar, ArrayList<Behandlung> behandlungsListe,
                  ArrayList<Beobachtung> beobachtungsListe, ArrayList<Ernte> ernteListe,
                  ArrayList<Arbeitsvorgang> arbeitsvorgaengeListe, Einstellungen einstellungen){
        this.volkliste = volkliste;
        this.inventar = inventar;
        this.behandlungsListe = behandlungsListe;
        this.beobachtungsListe = beobachtungsListe;
        this.ernteListe = ernteListe;
        this.arbeitsvorgaengeListe = arbeitsvorgaengeListe;
        this.einstellungen = einstellungen;
    }
    public String xmlExport() {

        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        String dataWrite = "";

        try {

            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag("", "EBEE");

            xmlSerializer.startTag("", "Voelker");
            insertVoelker(xmlSerializer, volkliste);
            xmlSerializer.endTag("", "Voelker");

            xmlSerializer.startTag("", "inventar");
            insertInventar(xmlSerializer, inventar);
            xmlSerializer.endTag("", "inventar");

            xmlSerializer.startTag("", "behandlungen");
            insertBehandlung(xmlSerializer, behandlungsListe);
            xmlSerializer.endTag("", "behandlungen");

            xmlSerializer.startTag("", "beobachtungen");
            insertBeobachtung(xmlSerializer, beobachtungsListe);
            xmlSerializer.endTag("", "beobachtungen");

            xmlSerializer.startTag("", "ernten");
            insertErnte(xmlSerializer, ernteListe);
            xmlSerializer.endTag("", "ernten");

            xmlSerializer.startTag("", "arbeitsvorgaenge");
            insertArbeitsvorgaenge(xmlSerializer, arbeitsvorgaengeListe);
            xmlSerializer.endTag("", "arbeitsvorgaenge");

            xmlSerializer.startTag("", "einstellungen");
            insertEinstellungen(xmlSerializer, einstellungen);
            xmlSerializer.endTag("", "einstellungen");


            xmlSerializer.endTag("", "EBEE");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            dataWrite = writer.toString();


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();

        } catch (IllegalStateException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return dataWrite;
    }

    private void insertInventar(XmlSerializer xmlSerializer, Inventar inventar) throws IOException {

        xmlSerializer.startTag("","zargen");
        xmlSerializer.text(String.valueOf(inventar.getZargen()));
        xmlSerializer.endTag("","zargen");

        xmlSerializer.startTag("","rahmen");
        xmlSerializer.text(String.valueOf(inventar.getRahmen()));
        xmlSerializer.endTag("","rahmen");

        xmlSerializer.startTag("","mittelwaende");
        xmlSerializer.text(String.valueOf(inventar.getMittelwaende()));
        xmlSerializer.endTag("","mittelwaende");

        xmlSerializer.startTag("","absperrgitter");
        xmlSerializer.text(String.valueOf(inventar.getAbsperrgitter()));
        xmlSerializer.endTag("","absperrgitter");

        xmlSerializer.startTag("","fluglochkeil");
        xmlSerializer.text(String.valueOf(inventar.getFluglochkeil()));
        xmlSerializer.endTag("","fluglochkeil");

        xmlSerializer.startTag("","windel");
        xmlSerializer.text(String.valueOf(inventar.getWindel()));
        xmlSerializer.endTag("","windel");

        xmlSerializer.startTag("","ameisensaeure");
        xmlSerializer.text(String.valueOf(inventar.getAmeisensaeure()));
        xmlSerializer.endTag("","ameisensaeure");

        xmlSerializer.startTag("","oxalsaeure");
        xmlSerializer.text(String.valueOf(inventar.getOxalsaeure()));
        xmlSerializer.endTag("","oxalsaeure");

        xmlSerializer.startTag("","milchsaeure");
        xmlSerializer.text(String.valueOf(inventar.getMilchsaeure()));
        xmlSerializer.endTag("","milchsaeure");
    }

    private void insertVoelker(XmlSerializer xmlSerializer, ArrayList<Volk> voelker) throws IOException {

        Volk volkObject;

        for (int i=0; i<voelker.size(); i++) {
            volkObject = voelker.get(i);
            xmlSerializer.startTag("", "Volk");
            xmlSerializer.attribute("", "Nr", String.valueOf(i+1));

            xmlSerializer.startTag("", "id");
            xmlSerializer.text(String.valueOf(volkObject.getVolkId()));
            xmlSerializer.endTag("", "id");

            xmlSerializer.startTag("", "name");
            xmlSerializer.text(volkObject.getVolkName());
            xmlSerializer.endTag("", "name");

            xmlSerializer.startTag("", "standort");
            xmlSerializer.text(volkObject.getStandort());
            xmlSerializer.endTag("", "standort");

            xmlSerializer.startTag("", "timestampCreate");
            xmlSerializer.text(String.valueOf(volkObject.getTimestampCreate()));
            xmlSerializer.endTag("", "timestampCreate");

            xmlSerializer.startTag("", "timestampModify");
            xmlSerializer.text(String.valueOf(volkObject.getTimestampModify()));
            xmlSerializer.endTag("", "timestampModify");

            xmlSerializer.startTag("", "typ");
            xmlSerializer.text(volkObject.getVolkTyp());
            xmlSerializer.endTag("", "typ");

            xmlSerializer.startTag("", "koenigin");
            xmlSerializer.text(String.valueOf(volkObject.getKönigin()));
            xmlSerializer.endTag("", "koenigin");

            xmlSerializer.startTag("", "absperrgitter");
            xmlSerializer.text(String.valueOf(volkObject.getAbsperrgitter()));
            xmlSerializer.endTag("", "absperrgitter");

            xmlSerializer.startTag("", "fluglochkeil");
            xmlSerializer.text(volkObject.getFluglochkeil());
            xmlSerializer.endTag("", "fluglochkeil");

            xmlSerializer.startTag("", "windel");
            xmlSerializer.text(String.valueOf(volkObject.getAbsperrgitter()));
            xmlSerializer.endTag("", "windel");

            xmlSerializer.startTag("","Drohnenrahmen");
            insertDrohnenrahmen(xmlSerializer, volkObject.getPosDrohnenrahmenList());
            xmlSerializer.endTag("","Drohnenrahmen");

            xmlSerializer.startTag("", "honigmenge");
            xmlSerializer.text(String.valueOf(volkObject.getMengeHonig()));
            xmlSerializer.endTag("", "honigmenge");

            xmlSerializer.startTag("", "notiz");
            xmlSerializer.text(volkObject.getNotiz());
            xmlSerializer.endTag("", "notiz");

            xmlSerializer.startTag("", "behandlung");
            xmlSerializer.text(String.valueOf(volkObject.getBehandlung()));
            xmlSerializer.endTag("", "behandlung");

            xmlSerializer.endTag("", "Volk");
        }
    }

    private void insertDrohnenrahmen(XmlSerializer xmlSerializer, ArrayList<Boolean> posDrohnenrahmen) throws IOException {
        boolean drohnenramenObject;
        for (int i=0; i<posDrohnenrahmen.size(); i++) {
            drohnenramenObject = posDrohnenrahmen.get(i);
            xmlSerializer.startTag("","Rahmen");
            xmlSerializer.attribute("","pos", String.valueOf(i+1));
            xmlSerializer.text(String.valueOf(drohnenramenObject));
            xmlSerializer.endTag("","Rahmen");

        }
    }

    private void insertBehandlung(XmlSerializer xmlSerializer, ArrayList<Behandlung> behandlung) throws IOException {
        Behandlung behandlungObject;
        for (int i = 0; i < behandlung.size(); i++) {
            behandlungObject = behandlung.get(i);
            xmlSerializer.startTag("", "behandlung");
            xmlSerializer.attribute("", "Nr", String.valueOf(i + 1));

            xmlSerializer.startTag("", "behandlungsart");
            xmlSerializer.text(behandlungObject.getArtDerBehandlung());
            xmlSerializer.endTag("", "behandlungsart");

            xmlSerializer.startTag("", "menge");
            xmlSerializer.text(String.valueOf(behandlungObject.getMenge()));
            xmlSerializer.endTag("", "menge");

            xmlSerializer.startTag("", "windelEingesetzt");
            xmlSerializer.text(String.valueOf(behandlungObject.getWindelEingesetzt()));
            xmlSerializer.endTag("", "windelEingesetzt");

            xmlSerializer.startTag("", "text");
            xmlSerializer.text(behandlungObject.getText());
            xmlSerializer.endTag("", "text");

            xmlSerializer.startTag("", "aktiv");
            xmlSerializer.text(String.valueOf(behandlungObject.getIsActiv()));
            xmlSerializer.endTag("", "aktiv");

            xmlSerializer.startTag("", "behandlungsende");
            xmlSerializer.text(String.valueOf(behandlungObject.getTimestampFinish()));
            xmlSerializer.endTag("", "behandlungsende");

            xmlSerializer.endTag("", "behandlung");
        }
    }

    private void insertBeobachtung(XmlSerializer xmlSerializer, ArrayList<Beobachtung> beobachtung) throws IOException {
        Beobachtung beobachtungsObject;
        for (int i = 0; i < beobachtung.size(); i++) {
            beobachtungsObject = beobachtung.get(i);
            xmlSerializer.startTag("","beobachtung");
            xmlSerializer.attribute("","Id", String.valueOf(i+1));

            xmlSerializer.startTag("","teilBeobachtungen");
            insertTeilBeobachtung(xmlSerializer, beobachtungsObject.getTeilBeobachtungList());
            xmlSerializer.endTag("","teilBeobachtungen");

            xmlSerializer.endTag("","beobachtung");
        }
    }

    private void insertTeilBeobachtung(XmlSerializer xmlSerializer, ArrayList<TeilBeobachtung> teilBeobachtung) throws IOException {
        TeilBeobachtung beobachtungsObject;
        for (int i = 0; i < teilBeobachtung.size(); i++){
            beobachtungsObject = teilBeobachtung.get(i);
            xmlSerializer.startTag("","teilBeobachtung");
            xmlSerializer.attribute("","Nr", String.valueOf(i+1));

            xmlSerializer.startTag("", "beobachtungsId");
            xmlSerializer.text(String.valueOf(beobachtungsObject.getBeobachtungId()));
            xmlSerializer.endTag("", "beobachtungsId");

            xmlSerializer.startTag("", "teilBeobachtungsId");
            xmlSerializer.text(String.valueOf(beobachtungsObject.getTeilBeobachtungID()));
            xmlSerializer.endTag("","teilBeobachtungsId");

            xmlSerializer.startTag("", "art");
            xmlSerializer.text(beobachtungsObject.getArtDerBeobachtung());
            xmlSerializer.endTag("","art");

            xmlSerializer.startTag("", "notiz");
            xmlSerializer.text(beobachtungsObject.getNotiz());
            xmlSerializer.endTag("","notiz");

            xmlSerializer.endTag("","teilBeobachtung");
        }
    }

    private void insertErnte(XmlSerializer xmlSerializer, ArrayList<Ernte> ernten) throws IOException {
        Ernte ernteObject;
        for (int i = 0; i < ernten.size(); i++){
            ernteObject =ernten.get(i);
            xmlSerializer.startTag("","ernte");
            xmlSerializer.attribute("","Nr", String.valueOf(i+1));

            xmlSerializer.startTag("", "id");
            xmlSerializer.text(String.valueOf(ernteObject.getErnteId()));
            xmlSerializer.endTag("","id");

            xmlSerializer.startTag("", "wabenanzahl");
            xmlSerializer.text(String.valueOf(ernteObject.getAnzahlWaben()));
            xmlSerializer.endTag("","wabenanzahl");

            xmlSerializer.startTag("", "sorte");
            xmlSerializer.text(ernteObject.getHonigSorte());
            xmlSerializer.endTag("","sorte");

            xmlSerializer.startTag("", "wassergehalt");
            xmlSerializer.text(String.valueOf(ernteObject.getWassergehalt()));
            xmlSerializer.endTag("","wassergehalt");

            xmlSerializer.startTag("", "menge");
            xmlSerializer.text(String.valueOf(ernteObject.getMenge()));
            xmlSerializer.endTag("","menge");

            xmlSerializer.startTag("", "notiz");
            xmlSerializer.text(ernteObject.getNotiz());
            xmlSerializer.endTag("","notiz");

            xmlSerializer.endTag("","ernte");
        }
    }

    private void insertArbeitsvorgaenge(XmlSerializer xmlSerializer, ArrayList<Arbeitsvorgang> arbeiten) throws IOException {
        Arbeitsvorgang arbeitsvorgangObject;
        for (int i = 0; i < arbeiten.size(); i++) {
            arbeitsvorgangObject = arbeiten.get(i);
            xmlSerializer.startTag("", "arbeitsvorgang");
            xmlSerializer.attribute("", "Nr", String.valueOf(i + 1));

            xmlSerializer.startTag("", "id");
            xmlSerializer.text(String.valueOf(arbeitsvorgangObject.getArbeitsvorgangId()));
            xmlSerializer.endTag("", "id");

            xmlSerializer.startTag("", "zargenanzahl");
            xmlSerializer.text(String.valueOf(arbeitsvorgangObject.getAnzahlZargen()));
            xmlSerializer.endTag("", "zargenanzahl");

            xmlSerializer.startTag("", "absperrgitter");
            xmlSerializer.text(String.valueOf(arbeitsvorgangObject.getAbsperrgitter()));
            xmlSerializer.endTag("", "absperrgitter");

            xmlSerializer.startTag("", "wabenanzahl");
            xmlSerializer.text(String.valueOf(arbeitsvorgangObject.getAnzahlWaben()));
            xmlSerializer.endTag("", "wabenanzahl");

            xmlSerializer.startTag("", "fluglochkeil");
            xmlSerializer.text(arbeitsvorgangObject.getFluglochkeil());
            xmlSerializer.endTag("", "fluglochkeil");

            xmlSerializer.startTag("", "drohnenrahmen");
            insertDrohnenrahmen(xmlSerializer, arbeitsvorgangObject.getDrohnenrahmen());
            xmlSerializer.endTag("", "drohnenrahmen");

            xmlSerializer.startTag("", "fluglochkeilWechsel");
            xmlSerializer.text(String.valueOf(arbeitsvorgangObject.getFluglochkeilWechsel()));
            xmlSerializer.endTag("", "fluglochkeilWechsel");

            xmlSerializer.startTag("", "absperrgitterWechsel");
            xmlSerializer.text(String.valueOf(arbeitsvorgangObject.getAbsperrgitterWechsel()));
            xmlSerializer.endTag("", "absperrgitterWechsel");

            xmlSerializer.startTag("", "drohnenrahmenWechsel");
            xmlSerializer.text(String.valueOf(arbeitsvorgangObject.getDrohnenrahmenWechsel()));
            xmlSerializer.endTag("", "drohnenrahmenWechsel");

            xmlSerializer.endTag("", "arbeitsvorgang");
        }
    }

    private void insertEinstellungen(XmlSerializer xmlSerializer, Einstellungen einstellungen) throws IOException {

        xmlSerializer.startTag("", "id");
        xmlSerializer.text(String.valueOf(einstellungen.getEinstellungId()));
        xmlSerializer.endTag("", "id");

        xmlSerializer.startTag("", "mail");
        xmlSerializer.text(einstellungen.getEMailAdresse());
        xmlSerializer.endTag("", "mail");

        xmlSerializer.startTag("", "aufgeloesteAnzeigen");
        xmlSerializer.text(String.valueOf(einstellungen.getAufgelosteVoelkerAnzeigen()));
        xmlSerializer.endTag("", "aufgeloesteAnzeigen");
    }

}





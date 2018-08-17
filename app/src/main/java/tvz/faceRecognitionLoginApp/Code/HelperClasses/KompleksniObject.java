package tvz.faceRecognitionLoginApp.Code.HelperClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class KompleksniObject implements Parcelable {

    private String ime;

    private String prezime;

    private boolean ninja;

    @Override
    public int describeContents() {
        int result = ime.hashCode();
        result = 31 * result + prezime.hashCode();
        result = 31 * result + (ninja ? 1 : 0);
        return result;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        List<Object> listaObjekata = new ArrayList<Object>();
        listaObjekata.add(ime);
        listaObjekata.add(prezime);
        listaObjekata.add(ninja);
        dest.writeList(listaObjekata);
    }

    public static final Creator<KompleksniObject> CREATOR = new Creator<KompleksniObject>() {
        @Override
        public KompleksniObject createFromParcel(Parcel source) {
            return new KompleksniObject(source);
        }

        @Override
        public KompleksniObject[] newArray(int size) {
            return new KompleksniObject[size];
        }

    };

    public KompleksniObject(String ime, String prezime, boolean ninja) {
        this.ime = ime;
        this.prezime = prezime;
        this.ninja = ninja;
    }

    public KompleksniObject(Parcel source) {
        List<Object> objektiKlase = new ArrayList<Object>();
        source.readList(objektiKlase, null);
        this.ime = (String)objektiKlase.get(0);
        this.prezime = (String)objektiKlase.get(1);
        this.ninja = (boolean)objektiKlase.get(2);
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public boolean isNinja() {
        return ninja;
    }

    public void setNinja(boolean ninja) {
        this.ninja = ninja;
    }
}

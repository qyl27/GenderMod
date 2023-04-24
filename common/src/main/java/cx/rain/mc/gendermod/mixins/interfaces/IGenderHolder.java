package cx.rain.mc.gendermod.mixins.interfaces;

public interface IGenderHolder {
    String getGender();
    void setGender(String gender);

    boolean hasTransformedGender();
    void setTransformedGender(boolean hasTransformed);
}

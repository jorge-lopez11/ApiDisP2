package com.grupo04.API_P2;

import java.util.UUID;

public class DatosNuevaZelanda {
    String ID;
    String MsCode;
    String Year;
    String EstCode;
    String Estimate;
    String SE;
    String LowerCIB;
    String UpperCIB;
    String Flag;

    public String getId() {
        return ID;
    }

    public void setId(String id) {ID = id;}

    public String getMsCode(){
        return MsCode;
    }
    public String getYear(){
        return Year;
    }
    public String getEstCode(){
        return EstCode;
    }
    public String getEstimate(){
        return Estimate;
    }
    public String getSE(){
        return SE;
    }
    public String getLowerCIB(){
        return LowerCIB;
    }
    public String getUpperCIB(){
        return UpperCIB;
    }
    public String getFlag(){
        return Flag;
    }
    public void setMsCode(String _MsCode){
        this.MsCode = _MsCode;
    }
    public void setEstCode(String _EstCode){
        this.EstCode = _EstCode;
    }
    public void setYear(String _Year){
        this.Year = _Year;
    }
    public void setEstimate(String _Estimate){
        this.Estimate = _Estimate;
    }
    public void setSE(String _SE){
        this.SE = _SE;
    }
    public void setLowerCIB(String _LowerCIB){
        this.LowerCIB = _LowerCIB;
    }
    public void setUpperCIB(String _UpperCIB){
        this.UpperCIB = _UpperCIB;
    }
    public void setFlag(String _Flag){
        this.Flag = _Flag;
    }
    public DatosNuevaZelanda(){
        this.MsCode = null;
        this.Year = null;
        this.EstCode = null;
        this.Estimate = null;
        this.SE = null;
        this.LowerCIB = null;
        this.UpperCIB = null;
        this.Flag = null;
    }
    public DatosNuevaZelanda(String MsCode, String Year, String EstCode, String Estimate, String SE, String LowerCIB, String UpperCIB, String Flag){
        this.MsCode = MsCode;
        this.Year = Year;
        this.EstCode = EstCode;
        this.Estimate = Estimate;
        this.SE = SE;
        this.LowerCIB = LowerCIB;
        this.UpperCIB = UpperCIB;
        this.Flag = Flag;
    }

    @Override
    public String toString() {
        return "Registro: {" +
                "Id=" + ID +
                ", MsCode='" + MsCode + '\'' +
                ", Year='" + Year + '\'' +
                ", EstCode='" + EstCode + '\'' +
                ", Estimate='" + Estimate + '\'' +
                ", SE='" + SE + '\'' +
                ", LowerCIB='" + LowerCIB + '\'' +
                ", UpperCIB='" + UpperCIB + '\'' +
                ", Flag='" + Flag + '\'' +
                '}';
    }
}

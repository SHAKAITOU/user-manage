package cn.caam.gs.common.html;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class HtmlViewBaseHelper extends HtmlBaseHelper {
    
    @Autowired
    private HtmlSpanHelper htmlSpanHelper0;
    private static HtmlSpanHelper htmlSpanHelper;
    
    @Autowired
    private HtmlEnumBaseHelper htmlEnumHelper0;
    private static HtmlEnumBaseHelper htmlEnumHelper;
    
    @Autowired
    private HtmlContextHelper htmlContextHelper0;
    private static HtmlContextHelper htmlContextHelper;
    
    @Autowired
    private HtmlTextAreaHelper htmlTextAreaHelper0;
    private static HtmlTextAreaHelper htmlTextAreaHelper;
    
    @Autowired
    private HtmlTextHelper htmlTextHelper0;
    private static HtmlTextHelper htmlTextHelper;
    
    @Autowired
    private HtmlRadioHelper htmlRadioHelper0;
    private static HtmlRadioHelper htmlRadioHelper;
    
    @Autowired
    private HtmlButtonHelper htmlButtonHelper0;
    private static HtmlButtonHelper htmlButtonHelper;
    
    @Autowired
    private HtmlNumberHelper htmlNumberHelper0;
    private static HtmlNumberHelper htmlNumberHelper;
    
    @Autowired
    private HtmlPasswordHelper htmlPasswordHelper0;
    private static HtmlPasswordHelper htmlPasswordHelper;
    
    @Autowired
    private HtmlFileHelper htmlFileHelper0;
    private static HtmlFileHelper htmlFileHelper;
    
    @Autowired
    private HtmlImageHelper htmlImageHelper0;
    private static HtmlImageHelper htmlImageHelper;
    
    @Autowired
    private HtmlDateInputHelper htmlDateInputHelper0;
    private static HtmlDateInputHelper htmlDateInputHelper;
    
    @Autowired
    private HtmlCheckBoxHelper htmlCheckBoxHelper0;
    private static HtmlCheckBoxHelper htmlCheckBoxHelper;
    
    @Autowired
    private HtmlHiddenHelper htmlHiddenHelper0;
    private static HtmlHiddenHelper htmlHiddenHelper;
    
    @Autowired
    private HtmlDivRowHelper htmlDivRowHelper0;
    private static HtmlDivRowHelper htmlDivRowHelper;
    
    @Autowired
    private HtmlDivContainerHelper htmlDivContainerHelper0;
    private static HtmlDivContainerHelper htmlDivContainerHelper;
    
    @Autowired
    private HtmlTableHelper htmlTableHelper0;
    private static HtmlTableHelper htmlTableHelper;
    
    @Autowired
    private HtmlTrHelper htmlTrHelper0;
    private static HtmlTrHelper htmlTrHelper;
    
    @Autowired
    private HtmlThHelper htmlThHelper0;
    private static HtmlThHelper htmlThHelper;
    
    @Autowired
    private HtmlTdHelper htmlTdHelper0;
    private static HtmlTdHelper htmlTdHelper;
    
    @Autowired
    private HtmlBorderCardHelper htmlBorderCardHelper0;
    private static HtmlBorderCardHelper htmlBorderCardHelper;
    
    
    @Autowired
    private HtmlSelectHelper htmlSelectHelper0;
    private static HtmlSelectHelper htmlSelectHelper;

    @PostConstruct
    private void initStaticDao () {
        htmlSpanHelper = htmlSpanHelper0;
        htmlEnumHelper = htmlEnumHelper0;
        htmlContextHelper = htmlContextHelper0;
        htmlTextAreaHelper = htmlTextAreaHelper0;
        htmlTextHelper = htmlTextHelper0;
        htmlRadioHelper = htmlRadioHelper0;
        htmlButtonHelper = htmlButtonHelper0;
        htmlNumberHelper = htmlNumberHelper0;
        htmlPasswordHelper = htmlPasswordHelper0;
        htmlFileHelper = htmlFileHelper0;
        htmlImageHelper = htmlImageHelper0;
        htmlDateInputHelper = htmlDateInputHelper0;
        htmlCheckBoxHelper = htmlCheckBoxHelper0;
        htmlHiddenHelper = htmlHiddenHelper0;
        htmlDivRowHelper = htmlDivRowHelper0;
        htmlDivContainerHelper = htmlDivContainerHelper0;
        htmlTableHelper = htmlTableHelper0;
        htmlTrHelper = htmlTrHelper0;
        htmlThHelper = htmlThHelper0;
        htmlTdHelper = htmlTdHelper0;
        htmlBorderCardHelper = htmlBorderCardHelper0;
        htmlSelectHelper = htmlSelectHelper0;
    }
    //--------------------------------------------------------------------------------------------------------
    //util func
    //--------------------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------------------
    //span
    //--------------------------------------------------------------------------------------------------------
    public static HtmlSpanHelper span() {
        return htmlSpanHelper;
    }
    //--------------------------------------------------------------------------------------------------------
    //enum span
    //--------------------------------------------------------------------------------------------------------
    public static HtmlEnumBaseHelper enumSpan() {
        return htmlEnumHelper;
    }

    //--------------------------------------------------------------------------------------------------------
    //labelText
    //--------------------------------------------------------------------------------------------------------
    public static HtmlTextHelper text() {
        return htmlTextHelper;
    }

    
    //--------------------------------------------------------------------------------------------------------
    //labelPassword
    //--------------------------------------------------------------------------------------------------------
    public static HtmlPasswordHelper password() {
        return htmlPasswordHelper;
    }
    
    //--------------------------------------------------------------------------------------------------------
    //labelNumber
    //--------------------------------------------------------------------------------------------------------
    public static HtmlNumberHelper number() {
        return htmlNumberHelper;
    }
    
    //--------------------------------------------------------------------------------------------------------
    //labelContext
    //--------------------------------------------------------------------------------------------------------
    public static HtmlContextHelper context() {
        return htmlContextHelper;
    }
    //--------------------------------------------------------------------------------------------------------
    //labelTextArea
    //--------------------------------------------------------------------------------------------------------
    public static HtmlTextAreaHelper textArea() {
        return htmlTextAreaHelper;
    }
    
    //--------------------------------------------------------------------------------------------------------
    //radio
    //--------------------------------------------------------------------------------------------------------
    public static HtmlRadioHelper radio() {
        return htmlRadioHelper;
    }
    
    //--------------------------------------------------------------------------------------------------------
    //button 
    //--------------------------------------------------------------------------------------------------------
    public static HtmlButtonHelper button() {
        return htmlButtonHelper;
    }
    
    //--------------------------------------------------------------------------------------------------------
    //labelFile
    //--------------------------------------------------------------------------------------------------------
    public static HtmlFileHelper file() {
        return htmlFileHelper;
    }
    //--------------------------------------------------------------------------------------------------------
    //labelImg
    //--------------------------------------------------------------------------------------------------------
    public static HtmlImageHelper image() {
        return htmlImageHelper;
    }
    //--------------------------------------------------------------------------------------------------------
    //labelDate
    //--------------------------------------------------------------------------------------------------------
    public static HtmlDateInputHelper dateInput() {
        return htmlDateInputHelper;
    }
    //--------------------------------------------------------------------------------------------------------
    //hidden
    //--------------------------------------------------------------------------------------------------------
    public static HtmlHiddenHelper hidden() {
        return htmlHiddenHelper;
    }

    
    //--------------------------------------------------------------------------------------------------------
    //checkbox 
    //--------------------------------------------------------------------------------------------------------
    public static HtmlCheckBoxHelper checkBox() {
        return htmlCheckBoxHelper;
    }

    
    //--------------------------------------------------------------------------------------------------------
    //div row
    //--------------------------------------------------------------------------------------------------------
    public static HtmlDivRowHelper divRow() {
        return htmlDivRowHelper;
    }
    //--------------------------------------------------------------------------------------------------------
    //div container
    //--------------------------------------------------------------------------------------------------------
    public static HtmlDivContainerHelper divContainer() {
        return htmlDivContainerHelper;
    }
    //--------------------------------------------------------------------------------------------------------
    //table
    //--------------------------------------------------------------------------------------------------------
    public static HtmlTableHelper table() {
        return htmlTableHelper;
    }
    
    //--------------------------------------------------------------------------------------------------------
    //tr
    //--------------------------------------------------------------------------------------------------------
    public static HtmlTrHelper tr() {
        return htmlTrHelper;
    }
    
    //--------------------------------------------------------------------------------------------------------
    //th
    //--------------------------------------------------------------------------------------------------------
    public static HtmlThHelper th() {
        return htmlThHelper;
    }
    
    
    //--------------------------------------------------------------------------------------------------------
    //td
    //--------------------------------------------------------------------------------------------------------
    public static HtmlTdHelper td() {
        return htmlTdHelper;
    }
    //--------------------------------------------------------------------------------------------------------
    //card
    //--------------------------------------------------------------------------------------------------------
    public static HtmlBorderCardHelper borderCard() {
        return htmlBorderCardHelper;
    }
    //--------------------------------------------------------------------------------------------------------
    //select
    //--------------------------------------------------------------------------------------------------------
    public static HtmlSelectHelper select() {
        return htmlSelectHelper;
    }
    
    //--------------------------------------------------------------------------------------------------------
    //form
    //--------------------------------------------------------------------------------------------------------
    public static String getForm(String name, String... contexts) {
        StringBuffer sb = new StringBuffer();
        sb.append("<form id='"+convertNameDotForId(name)+"' name='"+name+"' role='form' method='POST' class='form-horizontal'>");
        for(String context : contexts) {
            sb.append(context);
        }
        sb.append("</form>");
        return sb.toString();
    }
    
    public static String getFormForUpload(String name, String... contexts) {
        StringBuffer sb = new StringBuffer();
        sb.append("<form id='"+convertNameDotForId(name)+"' name='"+name+"' role='form' method='POST' class='form-horizontal' enctype='multipart/form-data'>");
        for(String context : contexts) {
            sb.append(context);
        }
        sb.append("</form>");
        return sb.toString();
    }
    
    
    //--------------------------------------------------------------------------------------------------------
    //util func
    //--------------------------------------------------------------------------------------------------------
    public static String getNoImgDataString() {
        return "/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQ"
                + "EBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/2wBDAQEBAQEBAQEBAQEBAQEBAQEBAQEBA"
                + "QEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/wAARCAEvAZADASIA"
                + "AhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQ"
                + "AAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NT"
                + "Y3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjp"
                + "KWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QA"
                + "HwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxE"
                + "EBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERU"
                + "ZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqs"
                + "rO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEA"
                + "PwD++L+wdF5/4lGlHPrp1p/SIf5zR/YOi/8AQH0n/wAFtp/8arWoovLuvua2t5+S+4XKl0/rT/J"
                + "GT/YOi/8AQH0n/wAFtp/8ao/sHRf+gPpP/gttP/jVa1FO77v+v+GX3Byrt/St/kjJ/sHRf+gRpP"
                + "8A4LbP/wCNUf2Dov8A0B9J/wDBbaf/ABqtaii77v8Ar/hl9wcqXT+tP8kZP9haL/0B9J/8Ftp/8"
                + "bo/sHRf+gPpP/gttP8A41WtRRd93/X/AAy+4OVdv60/yRk/2Dov/QI0n/wW2n5f6r/6/vR/YOi/9"
                + "AfSf/Bbaf8Axqtaii77v+v+GX3Byrtt/wAD/Jf1cyf7B0X/AKA+k/8AgttP/jVH9g6L/wBAjSf/A"
                + "AW2n/xqtaii77v+v+GX3Byrt/Wn+SMn+wdF/wCgPpP/AILbT/41R/YOi/8AQH0n/wAFtp/8arWoo"
                + "u+7/r/hl9wcq7f0rf5Iyf7B0X/oEaT/AOC2z/8AjVH9haL/ANAfSf8AwW2n/wAbrWoou+7/AK/4Z"
                + "fcHKl0/rT/Jf02ZP9g6L/0B9J/8Ftp/8ao/sLRf+gPpP/gttP8A43WtRRd93/X/AAy+4OVLp/Wn+"
                + "SMn+wdF/wCgPpP/AILbT/41R/YWi/8AQH0n/wAFtp/8brWoou+7/r/hl9wWXb+tP8kZP9g6L/0B9"
                + "J/8Ftp/8ao/sHRf+gPpP/gttP8A41WtRRd93/X/AAy+4OVdv60/yX9NmT/YOi/9AfSf/Bbaf/GqP"
                + "7B0X/oD6T/4LbT/AONVrUUXfd/1/wAMvuDlXb+tP8l/TZk/2Dov/QH0n/wW2n/xqj+wtF/6A+k/+C"
                + "20/wDjda1FF33f9f8ADL7g5V27fha35Iyf7B0X/oD6T/4LbT/41R/YWi/9AfSf/Bbaf/G61qKLvu/"
                + "6/wCGX3BZdv60/wAkZP8AYOi/9AjSf/BbZ/8Axrv/APqxR/YOi/8AQH0n/wAFtp/8arWoou+7/r/h"
                + "l9wcqXT+tP8AJf02ZP8AYOi/9AfSf/Bbaf8Axqj+wdF/6BGkj/uG2n9Yq1qKLvv/AFp/kvuDlXb+"
                + "tP8AJGT/AGDov/QH0n/wW2n/AMao/sHRf+gPpP8A4LbT/wCNVrUUXfd/1/wy+4OVLp/Wn+SMn+wdF/"
                + "6A+k/+C20/+NUf2Dov/QH0n/wW2n/xqtaii77v+v8Ahl9wWXb+tP8AJGT/AGDov/QH0n/wW2n/AMao"
                + "/sLRf+gPpP8A4LbT/wCN1rUUXfd/1/wy+4LLt/Wn+SMn+wdF/wCgRpP/AILbT/41R/YOi/8AQI0n/w"
                + "AFtp/8arWoou+/9af5L7g5V2/pWt+X9XZk/wBg6L/0CNJH/cNtP6xUf2Dov/QH0n/wW2n/AMarWoou+"
                + "/8AWn+S+4OVdv6Vv8v6uzJ/sLRf+gPpP/gttP8A43R/YOi/9AjSf/Bbacf+Qv55rWoou+/9af5L7g5V"
                + "2/rT/JGT/YOi/wDQH0n/AMFtp/8AGqP7B0X/AKA+k/8AgttP/jVa1FF33f8AX/DL7g5V2/pWt+X9XZk/"
                + "2Dov/QH0n/wW2n/xqj+wdF/6BGkj/uG2n9Yq1qKLvv8A1p/kvuDlXb+lb/JGT/YOi/8AQI0n/wAFtp/8"
                + "aqSLR9Lt5Vng06wgmQgpLBZ28MinBXIeNFfG1mBAbBBINaVFL3u6t6Py8/JByrt/Wn+X9XYUUUUDCiik"
                + "5z7Y/P8Aw/WgBaKQnHbJPQf/AF6MN6j8v/r0ALRSc+o/I/40Yb1H5f8A16AFopMN6j8v06//AF/ejn1H"
                + "5f8A16AFopMN6j8v/r0Yb1H5f/XoAWikw3qPy/8Ar0c+o/L/AOvQAtFJhv7w/L/69HPqPyP+NAC0UmG9"
                + "R+X/ANejn1H5H/GgBaKTn1H5f/Xo59R+R/xoAWikw3qPy/8Ar0c+o/L/AOvQAtFJhvUfl/8AXo59R+X/"
                + "ANegBaKTDeo/L/69HPqPyP8AjQAtFJz6j8v/AK9HPqPyP+NAC0UmG/vD8v8A6/f/APVijn1H5f8A16A"
                + "FopOfUfl/9ejDeoH4f4mgBaKTDeo/L/69HPqPy/8Ar0ALRSYb1H5f/Xow3qPy/wDr0ALRSYb1H5f/AF"
                + "6OfUfkf8aAFopMN6j8v/r0Yb1H5f8A16AFopMN6gfh/iaMN6j8v/r0ALRSc+o/I/40Yb1H5dP1/nmgB"
                + "aKTn1H5f/XpaACimqSQc9QcfoKdQAUUUUAFFFFABRRTHGR9P5UAc1rvivT/AA/LDDfJcs06PJH9nSNu"
                + "IychvOmhHIHOCc9iMjOH/wALK0I9IdTPHH7i09v+nv1z/wDW78z8TuL/AEsYP/HpcgjJ/iZM/wAWOv"
                + "Hp6eleZ73/ALzf99H/ABr1MPgqNWnGpPmvJbJ2XTy11X6Hk18XXhUlCFmlbdXfTt6O/f5nuR+JOhf8"
                + "8dT7/wDLC0Pf/r79P8g8Uf8ACytC/wCeOp/9+LT19fteOn498dq8N3v/AHm/76P+NG5v7zfmf8a2/s"
                + "7Ddp/+B+nl5Iy+vYrtHbrHrprovX8D3L/hZWhcfutT64/1Fr15yP8Aj74/HpR/wsrQv+eOp8df3Frx"
                + "xn/n757dM+nvXhu5v7zevU9fXrRvf+83/fR/xo/s7D9p/wDgXp5eSD69itNI+fut6aeW+9z3L/hZWh"
                + "Y/1Op/9+bT0/6+/X39vej/AIWVoWT+51Ppx+5tPbH/AC99zx0H19fDdzf3m/M/40bm/vN+Z/xo/s7D"
                + "dp/+B+nl5IX13Fdovb7L8uytrb+rnuX/AAsrQuP3Op/9+bT1GP8Al77j6/0J/wALK0L/AJ46n/34tP"
                + "XHX7X6f/W7ivDdzf3m/M/40b3/ALzf99H/ABo/s7Ddp/8Agfp5eSH9exXaO3WL30vsvX+tvcf+FlaF"
                + "/wA8tT/78Wvvnj7XkcY5PA69+F/4WToX/PHU/wDvxa+n/X3zz6fT3rw3c395vzNG9/7zf99H/Gj+zs"
                + "N2n/4H6eXkgWOxXaOn91vtvpva/rfpue5f8LK0L/njqf8A34tPTP8Az98fQ8/gM0f8LJ0Ln9zqff8A"
                + "5Y2nt/09/X0/Dv4bub+835n/ABo3v/eb/vo/40f2dhu0/wDwP08vJB9exXaO9/hfl/wb/wDB09y/4W"
                + "ToXH7nU+v/ADxtPX/r79Oe/wBB0o/4WToX/PHU/wDvzaev/X36f57Dw3e/95v++j/jRvf+83/fR/xo"
                + "/s7Ddp9Pt9reXkg+vYrtH/wF+V+i87f8HT3L/hZWhd4dT7Z/cWvv/wBPZx069Pc9j/hZWhf88dT7f8"
                + "sLX0/6++efT6e9eG7m/vN+Z/xo3v8A3m/76P8AjR/Z2G7T6fb7W8vJB9exXaOn9167b6f1+K9y/wCF"
                + "laF/zx1Ppn/UWvp/198c5649O2aP+FlaFz+51Pv/AMsLT+t39fTPsOvhu5v7zfmf8aN7/wB5v++j/j"
                + "R/Z2G7T/8AA/Ty8kH17Fa6Q/8AAX5abaI9yPxK0L/njqff/ljaf/Jfpz36dB0o/wCFk6F/zx1P/vzae"
                + "v8A19+n+ew8N3N/eb8z/jRvf+83/fR/xo/s7Ddp9Pt9reXkg+u4rtHp9l+V9kuzt92z09y/4WVoXH7n"
                + "U+3/ACwtPfP/AC9/449xnB/wsrQv+eOp/wDfi19P+vvnn0z6e9eG73/vN/30f8aN7/3m/wC+j/jR/Z2"
                + "G7T/8D9PLyQLG4rTSPT7Pp5ev9be4/wDCytC4/dalz0/cWvPHH/L3xk569enbNL/wsrQv+eOp9/8Al"
                + "ha+3/T2M9evT3Hfw3c395vzPbp+VG9/7zf99H/Gj+zsP2n/AOBenl5L+tz69itdI69ovTbuu1/me5f8"
                + "LJ0L/njqff8A5Y2nqP8Ap79P8joQ/ErQv+eOpj6w2nrj/n7+np+fA8N3v/eb/vo/40bmPVm/M/40f2d"
                + "hu0+n2+1vLyQfXsV2jt1i/LsvX+np7kPiVoX/ADx1P3/c2nXnP/L39PX8ex/wsrQv+eOp/wDfi09Dnp"
                + "d889hz275rw3c395vzP+NG9/7zf99H/Gj+zsN2n/4H6eXkg+vYrS6jvr7u608vX+tvcv8AhZWhcfudT"
                + "56fuLXnjP8Az98d+v096P8AhZWhdodT78+Ran0/6e+e/I4+mOfDdzf3m/M/40bm/vN+Z/xo/s7D9p/+B"
                + "enl5L+tz69ie0ddfhflf5b/APAvp7l/wsrQv+eOp/8Afi09f+vv0z78ZPpR/wALJ0L/AJ46n/35tPX/"
                + "AK+/T/PYeG7m/vN+Z/xo3v8A3m/76P8AjR/Z2G7T6fb7W8vJB9dxXaP/AIC/Ltbs/wDhnp7l/wALK0L"
                + "vDqeO/wC5tPfP/L3649fx7H/CytC/546nnn/ljaen/X52PB5/xrw3c395vzP+NG5v7zfmf8aP7Ow3af"
                + "8A4H6eXkg+vYrtH/wH06W9eut+h7ifiVoQ/wCWOp9D/wAsLT0H/T3xye+PT3K/8LK0Ln9zqfGf+WFrx"
                + "0/6e8njuOO/Hfw3cx6s35n/ABo3N/eb8z/jR/Z2G7T/APA/Ty8kH17Fdo9Psvyulo/61Pcv+Fk6F/zx"
                + "1P8A78Wvr6/a8dPx747Uf8LK0Ln9zqfb/lhacc9x9ryOOecf0Hhu9/7zf99H/Gjc395uevJ5/Wj+zsN"
                + "2n0+32t5eSD69itNI/wDgL8vL+vnp7ifiVoQGfJ1P/vzaehJ63g9PX2GSa7LSdUh1iyiv7ZXWCYyhBI"
                + "u1/wB1NJCdwBZOqE/K7fXFfLhZj1LHvjJ6jp3Hf3r6C8Cr/wAUvp2OmbvHX+K+uWHUn+9zz3rkxeFpU"
                + "IxlTuruzu7vSy3+7T5nVhMTVrVHCpbSN1ZWvtvfqdpRSAYAHoKWuA9EKKKKACiiigApG6H6H+VLSN0P"
                + "0NAHi/xQ/wCQhpf/AF6XH/o2OvMK9P8Aih/yENL/AOvS4/8ARsf+f1615hXv4T/d6fofP4r+NL5BRRR"
                + "XSc4UUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAB"
                + "RRRQAUUUUAFFFFABRRRQAV9D+BP+RV0z63H/pZNXzxX0P4E/5FXTfrcf8ApZNXn5j/AAof4n+h35f/A"
                + "Br/AN2X4L/gnY0UUV457IUUUUAFFFFABSN0P0/z/wDX9qWkbofoaAPFvih/yEdL6/8AHrcf+jI/0/rz"
                + "0rzGvT/ih/yENL/69Lj/ANGx/wD6/wD61eYV7+E/3en6Hz+K/jS8kv1Ciiiuk5wooooAKKKKACiiigA"
                + "ooooAKKKKACiiigAooooAKKKKACiiigAooyPWjuB6kD8SQB+ZIH1IHU0AGCSAAT/uDJH13HGT7Y/nS7W"
                + "H3l2juSHwPx2bfzIrufD/AIGvNXC3F27WVizfKXjzPOh6mJGx5YIPySvnOcqoGK9LtvAvhy1QBrE3RB"
                + "B3XMssjH6hWVBj12+uTXFVxtGnO13J2s0u+m3Tr28tb3Oylg6lSKktLvS9utrO27319LeZ88kgA8qPQ"
                + "lgB+GeD9Ov65UKfl5BypIyVAIGAWHqQSOAe/Svo6Twf4clRk/syOEMDzC00b9OzK4+uDnOBxxmuH1v4"
                + "cmKN5tGuJJiAXFlcEFsDoIJiCA3LcPksQMMOcqnjqE2k1OMtbNydum6vbo99B1MHWppu8JJbWilfVJ2"
                + "0e13ueUj659+P6UVJJDLA7RTI8c0bFJI3Qo8bA/dZT1wcjf8AdbAK8ECo67YyUleOqOOUXF2ejQUUUU"
                + "xBRRRQAUUUUAFFFFABRRRQAHof6dfwr6G8B/8AIrab163H0/4/Jun9a+ea+h/An/Iq6b9bj/0smrz8x"
                + "/hQ/wAT/Q78v/ivzjJfdZ6efrodjRRRXjnshRRRQAUUUUAFI3Q/Q0tNb7p/z/n/ADnigDxj4of8hDS/+"
                + "vS4/wDRsf8An9eteYV6d8UP+Qjpf/Xrcev/AD0jH+e3415jXv4T/d6foeBiv40vRfLcKKKK6TmCiiigA"
                + "ooooAKKKKACiiigAoo74+v6Y/xpCQASSAACSSccD+p6AdSelAPRXez0X5fqL/X+Q7n0HoTgE5AOc0env/"
                + "nn0/GvSfC3gc38D32rmWCGeFhaWykxu4ZSUupcgsCo5jTHI2uRziuR17QbvQL37NcB2ik/497ryx5dyo5"
                + "JVw330BBdGUOBkhCCDWCxFJ1HTUk2t9rXv+j3fc2eHqxpqpKLSdmrrVrR/lsYlFH+ecg8d8HBx+FFbmIU"
                + "UUUAFFFFACH8fXvj059Bz3rvvAnh9dUvjf3kKyWNkzBVfBWa6XOEIzkpCQsrZG3fGoG4nFcCQGVlP8QK5B"
                + "5wwIwPqcDtjivo3wfZfZNA00YAM8H25wFwRJdkTDtkkROFOc5OQMryePHVfZUoqOjqNpvW6aaSt2ex24Gm"
                + "qlVuTvybJ+i/zf3vc6pVUKoCqAAAAAAAAOAB6DtTqQdB9BS14Z7YUwoPTP19exyMdOnHXPNPopNNrTfS3yd"
                + "wPM/H3htb2zfVrVFivbWNjNsAAnTgZcZ+YxDLDqeuMnNeJZ5J/wBkE/UKCfyBHTrxivrSWNZEkSRQ0boUZS"
                + "Mghshsg8cgjFfLOpWq2OoX1kvC2d7PbA88pE7Qrz1OQgJyO/Y5Fetl1WbjKlJ3UYp+mq/W+35Hk4+lGLjNL"
                + "WTab9Fov63uUqKKK9O346v+vkeaFFFFABRRRQAUUUUAFFFFABX0P4E/5FXTfrcf+lk1fO56H6H/ADxz+VfQ"
                + "/gP/AJFbTvrP6/8AP3N+H5fjXn5j/Ch/if6Hfl/8V+UW/VOyt8t/8tzsqKKK8c9kKKKKACiiigApG6H6Glp"
                + "G6H6GgDxf4of8hDS/+vS4/wDRsf8An9OteYV6f8UP+Qhpf/Xpcf8Ao2P/AD+nWvMK9/Cf7vT9D5/FfxpfIK"
                + "KKK6TnCiiigAooooAKKKKACiig57cn/P5n0HUngc0m0t9F1b2XqFr7bvZdX6dOvcCQOSQBkZJ6Aev/ANYck"
                + "8V6h4M8Gm48nV9WizDu32djINruVwUuJlIyEbOVifBwocjDAUeDPBjXDRavq0JSIMr2NnIDmUf89rlRjCk/"
                + "cQg9MkEEV7KqbQAMAAfdUAAcY4xzgDAGcn3rzMZi/wDl1Td7byXpe1+r1t0Sseng8JtVqLfaD6bWutlffuI"
                + "qbcgYCj7uABj2HXAAwMdMAYxWbrGkWms2b2d3EJEYExvgCSGUA7ZYm/hcE+mD0bgmtbGKK8qF4S51J8297+j"
                + "6ea7+R6binHlsuXa3b00Z8xa9oN34fvWtrlS0TbjbXIBEdxETlRvYlVlQjDp1I5AwQaxP6dceuM/j1r6j1bR"
                + "7TWbOWzvEDxvko3SSKTtJGwwQy9QOjDKsCCc/PGu6Fd6DeNbXALxOc2t0q5jnjzgNhcBZVGFkXuQCODk+5hc"
                + "Wqy5JO1RXv0vZLbv5rpv1seLisK6L5oq9N7PV29TDoo7A9M5xnrjJHP5UV2nGFFFFAAc9uuQB9SyjP4DJHT"
                + "nvX034blWbQ9IdCGU6ZZKSOfmjt443Gc9nVgf5nrXzJnHODxyMDJB6ce4znB4IBr2P4b6xC1m+hs48y0LvbB"
                + "iC0lq6hsLn7zRybw2S2AQOmAPPzKlz0oyT+F8zV9bJp/f1vppfbc7svko1ZJ7y0XlpFfLb7vU9VopqNkDscD"
                + "j/AD+vpSndkYIx3/zivGTTV0e01YWiikPAJ9AaYDWPBzjAAyeuMn+g59elfMPiCSObXdXkjYNHJqV0ysM4ZV"
                + "upcn8e3rXv3iXWY9G0i6u5H2v5bRwIAC8srgIu1CCGALEn04yOhr5qIOAW+Z2Us5Ocs8nzSEk85Lk9eh6Y6D1"
                + "MuhJOdRqycVFet7/13PLzGcXyQT96LbfZJpfj9wUUUV6h5YUUUUAFFFFABRRRQAUUUUAFfQ/gT/kVdN+tx/6"
                + "WTV88V9D+BP8AkVdN+tx/6WTV5+Y/wof4n+h35f8Axf8At2Xy0Wr8v1Oxooorxz2QooooAKKKKACkbofpS0j"
                + "dD9DQB4t8UD/xMdL/AOvW4HX/AKaRn/I/HpXmNen/ABQ/5CGl/wDXpcf+jY//ANf/ANavMK9/Cf7vT9D5/Ffx"
                + "pfK4UUUV0nOFFFFABRRRQAUUUHgE+mP14H5ngep4o16W01d+i6hp83ZLzba0/MQkAEngAZz1/Qcn6Dk16h4N8"
                + "GG4MWraxDiDAaytJBgyMMstzcA5JA4McYAO4KzcA5XwX4NMzQ6vqyFYkcS2llKo3SMMbbidRksigBokbAPBYY"
                + "yK9kRVC7QuF6YACqAOAABgAAdgMCvLxeMT/dUm+8paqzVtl+v4aM9PCYParVTTTTgrdNG2/O6+X5qqABRk/KB"
                + "jgAADGAABjAAA4p9FFeVbe27PVCiiimAVk6vo9lrFnJaXsW+NgxRxgPC+Mq8TAblZSARtIBx82eBWtRQrxkpx"
                + "bUk00030+fVaEyjGScZJNPo/63ufMWuaFd6Bd/Z7lS8bl2trleIblcEjkjiUDh0zu38gbSprE7A9QQOcYz69f"
                + "SvqLV9IstXs5LO8iDRuPkkH+shfnbLGxBwynBHY9CCOK+eNe0K70G9a3uVLxNk210EO24jByRkEokoGC6na3f"
                + "GCK9rCYxV4qMmlOKStr722qv1u9V5t9dPFxWGdGXNHWm9nbby+Xf5mJRR/T8f5UV3f195xhn/P9atWd5cafcQ"
                + "3drMbeaFiY5FOACcbkcAZMcgOGxwe9VaAMHPH0xz9fT+tJxTTUldPo/l/XzHGTi1KLs073R754f8AGdhq0SQ3"
                + "Lw2F+U2tFM22OZlziS3mYqjq5+byjhgOC2Rz2ySFiByOhyVwG6E4PTv29R65r5N5OQTwxyeMkHnlD8pQ8/wkY"
                + "PTjrr2ev63p422mqXcQ9N4lXGchdsyuCB/tKR36nNebUy68m6TtfWzWm/lr+nbselSzFqKVWKvor3bb/wCD63"
                + "02u1r9PFiN3GcEAYBPbngDkjHTHGawtW8RaZo8JkvriNJCr7LZWzO5A4AjGWG4lRucKFz714NceKvEdyjRy6v"
                + "cMGHOzybdiAMbS0Fuj+mQGAJAJ6ADCd5JX8yWR5JCMF5WaQ/iSSTn8Meg5qYZdK6dScbdVHV7eqe/knZdCqmY"
                + "Rs1Ti76Wb07fPutvl1N7xB4iuteu1uHTy4ImIt7ds4iUgDeV6FyBuLYIDYwQBxz55AHcBgSecluck9yOeQTjt"
                + "jiiivTpwVOEYR2j+Olv69WebObqScnu/wCuoUUHjrxyM59M8kevAOMZzjjNTyWlxHaxXksEy2s0skcdx5TNGzR"
                + "8sAQCOFzk9M5AJI5blGNnJpXdld9dxRi5aRTb3dl06fqQUUg55xjPHYZ2kjoOOCCP/rYpae5OwUUUUAFFFFAB"
                + "RRRQAHgGvobwGf8AiltNHobjv63k3btXzzX0P4E/5FXTfrcf+lk1efmP8KH+J/od+X/xX/hlb10v+Hf5O52NFF"
                + "FeOeyFFFFABRRRQAUjdD9DS0jdD9D/ACoA8X+KH/IQ0v8A69Lj/wBGx/8A6v8A61eYV6f8UP8AkIaX/wBelx/6"
                + "NjrzCvfwn+70/Q+fxX8aXyCiiiuk5wooooAKOe388f0NFBIHJ4Axk+gzjt19h3OB3o+dvN62/IL26N+S3YhIHJ"
                + "IAAJJPQADPP16ADJJ4r1Hwb4MNw0GraxARAds1jaOD+97pPcIRlQCN0SkYKlXIOSCeDPBhuGh1fVoj5QbfaWMo"
                + "AZtpBS4lGDhSMFYiRkfMc5AHsqpt4GAAMDAwMZyOCTjA4Ht0xXl4vGb0qW97Smuy3te1nd69Oy7enhMJr7Spqm"
                + "vdjtZ6O767X++6BBgdMYwMnrgDGBx0zzjoMkDpT6KK8s9XYKKKKACiiigAooooAKydX0i01mzksryMSRuSyuB+8"
                + "hlA/dyxt2ZDzzkHoeCQdaihNxfNHSSaaa0enn/X3ClFSTjJXTPmPXtBvdBvfs10C8b7ntbgD5J48AHsCssY5MZO"
                + "RweM1hnj2znAPBx646ivqPV9JtNYtJLO8jDxuCUcYEkLgHEkbkHawPOcEHowIzn5313QbzQLw21yu6Jsm2uQNsV"
                + "xGCdvzHIWVBxInuCBjk+3g8Wq8eWbSqdn10Wq/N6ddDxMThXRblHWm9VvePSz79/TcxKKPyODg46Z7gHjOM0V2"
                + "nGFFFFAPXfy/DYKKKKLK97K/wDSHd2tdtefy/yCjg+/BJA5IVepI7Dr9cUDkgc5Jx046ZyTngAZycV2vhPwlNrs"
                + "ovLpZYdLiYgkZV7srkGOIkNmLPzPIuFJJVRk1FSpClFzm0kuje/6/d8yoU5VJKMU2326eYnhPwlNrrrd3aNHpKO"
                + "CxxhrxozlYomPSPAAkcDGCVGa9tuNKsLvT302a1T7E0YjESqAEQBtpiA+6y8ZYHLZJJq9bW8dtFHDDGsMUIEccc"
                + "a7Y1RQQoC8gcYBPBJHJzxVjHuf0/w/z+WPBr4ideopKTjGL91Lt33er2v/AE/doYeFGHLZOT+NvW7t+Hou/mfNni"
                + "Pw3deHrlFfMljO220uyPvYH+qmPRHH8IONw981zf05H6/iPbvzxjjNfVF/p9tqVrPaXkCTwTKVKEAMDyQ6E52sp"
                + "wVI+YH1HB+e/Enhu68PXWxg01jMSba7C/LknIgl2gBJVBwMjDqA/BYV6eExca8VGdo1Iqy2XtLW2WyaXbfXrt52"
                + "Kwrpt1IK8L35VutUn57tveyT8kjm6KKK7jgCiiigAooooAK+h/An/Iq6b9bj/wBLJq+eK+h/An/Iq6Z9bj/0smr"
                + "z8x/hQ/xP9DvwH8X5S/JHY0UUV457IUUUUAFFFFABSN0P0NLSN0P0NAHi/wAUM/2jpf8A16XH5ebH7+v/AOrvXmF"
                + "en/FD/kIaX/16XH/o2P8Ar/nvXmFe/hP93p+h8/iv40vl/T/rYKKKK6TnCg9Djr2ooPAJ9PTrRp10QWe1tXsvXb7"
                + "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";
    }
    
}

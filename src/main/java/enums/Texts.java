package enums;

public class Texts {

    public enum sectionTitles {
        SECTION_TITLES("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
        public String home;
        public String contactForm;
        public String service;
        public String metalsAndColors;

        sectionTitles(String firstTab, String secondTab, String thirdTab, String forthTab) {
            this.home = firstTab;
            this.contactForm = secondTab;
            this.service = thirdTab;
            this.metalsAndColors = forthTab;
        }
    }

    public enum benefitTexts {
        BENEFIT_TEXTS("To include good practices\nand ideas from successful\nEPAM project",
                "To be flexible and\ncustomizable",
                "To be multiplatform",
                "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more" + "…");
        public String benefitOne;
        public String benefitTwo;
        public String benefitThree;
        public String benefitFour;

        benefitTexts(String one, String two, String three, String four) {
            this.benefitOne = one;
            this.benefitTwo = two;
            this.benefitThree = three;
            this.benefitFour = four;
        }
    }

    public enum aboveTexts {
        ABOVE_TEXTS("EPAM FRAMEWORK WISHES…", "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, SED DO EIUSMOD TEMPOR INCIDIDUNT " +
                "UT LABORE ET DOLORE MAGNA ALIQUA. UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI " +
                "UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT IN VOLUPTATE VELIT ESSE CILLUM DOLORE" +
                " EU FUGIAT NULLA PARIATUR.");
        public String epam;
        public String lorem;

        aboveTexts(String one, String two) {
            this.epam = one;
            this.lorem = two;
        }
    }

    public enum serviceOptions {
        SERVICE_OPTIONS("SUPPORT", "DATES", "COMPLEX TABLE", "SIMPLE TABLE", "USER TABLE", "TABLE WITH PAGES", "DIFFERENT ELEMENTS", "PERFORMANCE");
        public String support;
        public String dates;
        public String complexTable;
        public String simpleTable;
        public String userTable;
        public String tableWithWages;
        public String differentElements;
        public String performance;

        serviceOptions(String one, String two, String three, String four, String five, String six, String seven, String eight) {
            this.support = one;
            this.dates = two;
            this.complexTable = three;
            this.simpleTable = four;
            this.userTable = five;
            this.tableWithWages = six;
            this.differentElements = seven;
            this.performance = eight;
        }
    }

    public enum webElementTypes {
        WEB_ELEMENT_TYPES("checkbox", "radiobutton", "dropdown");
        public String chbx;
        public String radio;
        public String ddown;
        webElementTypes(String one, String two, String three){
            this.chbx = one;
            this.radio = two;
            this.ddown = three;
        }
    }

    public enum checkboxTexts {
        CHECKBOX_TEXTS("Water", "Earth", "Wind", "Fire");
        public String water;
        public String earth;
        public String wind;
        public String fire;

        checkboxTexts(String one, String two, String three, String four) {
            this.water = one;
            this.earth = two;
            this.wind = three;
            this.fire = four;
        }
    }

    public enum radiobuttonTexts {
        RADIOBUTTON_TEXTS("Gold", "Silver", "Bronze", "Selen");
        public String gold;
        public String silver;
        public String bronze;
        public String selen;

        radiobuttonTexts(String one, String two, String three, String four) {
            this.gold = one;
            this.silver = two;
            this.bronze = three;
            this.selen = four;
        }
    }

    public enum dropdownColors {
        DROPDOWN_COLORS("Red", "Green", "Blue", "Yellow");
        public String red;
        public String green;
        public String blue;
        public String yellow;

        dropdownColors(String one, String two, String three, String four) {
            this.red = one;
            this.green = two;
            this.blue = three;
            this.yellow = four;
        }
    }

    public enum rangeLogs {
        RANGE_LOGS("To", "From");
        public String to;
        public String from;

        rangeLogs(String one, String two) {
            this.to = one;
            this.from = two;
        }
    }

    public enum usersInTable {
        USERS_IN_TABLE("Roman", "Sergey Ivan", "Vladzimir", "Helen Bennett", "Yoshi Tannamuri", "Giovanni Rovelli");
        public String roman;
        public String ivan;
        public String vlad;
        public String helen;
        public String yoshi;
        public String giovanni;

        usersInTable(String one, String two, String three, String four, String five, String six){
            this.roman = one;
            this.ivan = two;
            this.vlad = three;
            this.helen = four;
            this.yoshi = five;
            this.giovanni = six;
        }



    }
}


/**
 * Created by Dmitrii_Miagkov on 1/19/2016.
 */
public class Plot {

    private static String printTextPerRole(String[] roles, String[] textLines){

        StringBuilder Plot = new StringBuilder();

        int lineNumber;

        for (String strRoles: roles){

            Plot.append('\n');
            Plot.append(strRoles + ":");
            Plot.append('\n');
            lineNumber =1;

            for (String strTxt: textLines){

                if (strTxt.startsWith(strRoles)){

                    Plot.append(lineNumber);
                    Plot.append(")\u0020");
                    String tmp = strTxt.substring(strTxt.indexOf(':') + 1);
                    Plot.append(tmp.trim());
                    //Plot.append(strTxt, (strTxt.indexOf(':')+1), strTxt.length());
                    Plot.append('\n');
                }

                lineNumber +=1;

            }
        }
        return String.valueOf(Plot + "\n");
    }

    public static void main (String[] args) {

        String[] roles = new String[] {"Foma", "Erema"};
        String[] textLines  = new String[] {"Foma:is a : good guy", "Erema : is a Foma bad guy", "Foma : It is ok", "Erema : it is not ok" };
        String Plot = printTextPerRole(roles,textLines);
        System.out.println(Plot);

    }

}

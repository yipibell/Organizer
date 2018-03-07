package Utility;

public class CommonCommands {
    public int SetNum(String StringofNum) {
        if (StringofNum.equalsIgnoreCase("")) {
            return 0;
        }
        Integer num = Integer.valueOf(StringofNum);
        return num;
    }
}

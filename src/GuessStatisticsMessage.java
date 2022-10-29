public class GuessStatisticsMessage {
    private String number;
    private String verb;
    private String pluralModifier;

    public String make(char candidate,int count){
        createPluralDependentMessageParts(count);
        return String.format("There %s %s %s%s",verb,number,candidate,pluralModifier);
    }

    public void createPluralDependentMessageParts(int count){
        if(count == 0){
            thereAreNoLetters();
        }else if(count == 1){{
            thereIsOneLetter();
        }}else{
            thereAreManyLetters(count);
        }
    }

    public void thereAreManyLetters(int count){
        number = Integer.toString(count);
        verb = "are";
        pluralModifier = "s";
    }

    public void thereIsOneLetter(){
        number = "1";
        verb = "is";
        pluralModifier = "";
    }

    public void thereAreNoLetters(){
        number = "0";
        verb = "are";
        pluralModifier = "s";
    }
}

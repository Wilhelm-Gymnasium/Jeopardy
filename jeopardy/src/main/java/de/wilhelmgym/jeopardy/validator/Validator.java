package de.wilhelmgym.jeopardy.validator;

import de.wilhelmgym.jeopardy.structure.Question;

//Börgi & Nilsi

public class Validator {

    //TODO Implement this method.
    //TODO Tip: create helper classes in the de.wilhelmgym.jeopardy.validator package.
    /**
     * Checks the congruence of an answer with the right answer of a given question.
     * Compares:
     * - String length //TODO
     * - Case //TODO
     * - Swapped letters //TODO
     * - Forgotten letters //TODO
     * - Other Typos //TODO Please specify more precisely.
     *
     * @param question The question with the right answer in it
     * @param answer The answer to check
     * @return A double value where 0 < x <= 1.
     * Zero should be returned with no congruence. One should be returned if the answer {@code equals()} the right answer.
     */

    public static final double MULTIPLIER_FOR_FORGOTTEN = 1.0;
    public static final double MULTIPLIER_FOR_SWAPPED = 1.0;
    public static final double MULTIPLIER_FOR_WRONG = 1.0;
    public static final double MULTIPLIER_FOR_ADDED = 1.0;
    public static final double MULTIPLIER_FOR_CASEFALSE  = 1.0;
    public static final double MULTIPLIER_FOR_ALL = 1.0;

    private String answer;
    private String right;


    public double validate(Question question, String answer) {

        this.right = "abcde"; //TODO question.getrightanswer();
        this.answer=answer;

        //right = right.toLowerCase();
        //answer = answer.toLowerCase();


        if (this.right.equals(this.answer)) {
            return 1;
        } else {

            int countOfWrongLetters = 0;
            int countOfSwappedLetters = 0;
            int countOfForgottenLetters = 0;
            int countOfAddedLetters = 0;
            int countOfCaseFalse=0;

            countOfCaseFalse=checkCaseOfLetters();


            ValidatorWorkingStructure org = new ValidatorWorkingStructure(this.right);

            org.setReferences(this.answer);





            /*
            for(int i = 0; i< right.length(); i++)
            {
                if(right.charAt(i+countOfForgottenLetters-countOfAddedLetters)!=answer.charAt(i)) //TODO NullPointerException Fixing
                {
                    if((i+countOfForgottenLetters-countOfAddedLetters+1<right.length())&&(right.charAt(i+countOfForgottenLetters-countOfAddedLetters+1)==answer.charAt(i)))
                    {
                        if(right.charAt(i+countOfForgottenLetters-countOfAddedLetters)==answer.charAt(i+1))
                        {
                            countOfSwappedLetters++;
                        }
                        else
                        {
                            countOfForgottenLetters++;
                            //TODO countOfAddedLetters
                        }
                    }
                    else
                    {
                        countOfWrongLetters++;
                    }
                }
            }
            */



            double imprecision = ((countOfWrongLetters*MULTIPLIER_FOR_WRONG+countOfSwappedLetters*MULTIPLIER_FOR_SWAPPED+countOfForgottenLetters*MULTIPLIER_FOR_FORGOTTEN+countOfAddedLetters*MULTIPLIER_FOR_ADDED+countOfCaseFalse*MULTIPLIER_FOR_CASEFALSE)/right.length())*MULTIPLIER_FOR_ALL;

            if(imprecision>1)
            {
                imprecision=1;
            }

            return 1-imprecision;


        }


    }

    public int checkSwappedLetters(String right, String answer)
    {
        return 0;
    }

    public int checkCaseOfLetters()
    {
        int steps =0;
        int countOfCaseFalse = 0;

        String rightLowerCase = this.right.toLowerCase();
        String answerLowerCase = this.answer.toLowerCase();

        if(right.length()<answer.length())
        {
            steps=right.length();
        }
        else
        {
            steps=answer.length();
        }

        for(int a=0; a<steps;a++)
        {
            if((right.charAt(a)!=answer.charAt(a)&&(rightLowerCase.charAt(a)==answerLowerCase.charAt(a))))
            {
                answer=""+answer.substring(0,a)+right.charAt(a)+answer.substring(a+1,answer.length());
                countOfCaseFalse++;
            }
        }

        return countOfCaseFalse;
    }

    public int checkAddedLetters(String right, String answer)
    {
        return 0;
    }

    public int checkForgottenLetters(String right, String answer)
    {
        return 0;
    }

    public int checkWrongLetters(String right, String answer)
    {
        return 0;
    }

}
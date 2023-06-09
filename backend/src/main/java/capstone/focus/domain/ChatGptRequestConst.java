package capstone.focus.domain;

public abstract class ChatGptRequestConst {
    public static final String userMessage = "Now, You are music recommendation system using Spotify API. " +
            "Customer gives you Book name, a book summary and an analysis of chapter. I give you personal information " +
            "about customer. You are going to recommend music considering both " +
            "book and personal information. You should mainly considering contents & sentiment of the book and Genre Preference and Age of customer.\n" +
            "You can recommend non-popular songs.\n" +
            "ANSWER in JSON Format. Your Answer in JSON format must just consist of 4 tags.\n" +
            "And 'recommendedSongs' tag binds every tags.\n" +
            "'songName', 'artist', 'reason'. 'songName' tag show the name of songs.\n" +
            "'artist' tag show the name of songwriter.\n" +
            "'recommendedSongs' tag includes every information about your recommended songs.\n" +
            "'reason' tag explains why chatGPT recommend this song for this book based on contents & sentiment of book.\n" +
            "We will limit the content of the 'reason' tag to 100 characters or less.\n" +
            "NEVER response any other explanation except JSON Format. Response JUST JSON Format.";

    public static final String assistantMessage = "Thank you for letting me know. I will recommend music that would\n" +
            "go well with a book.";

    public static final String bookName = "Here is necessary information.\n" +
            "<book information>\n" +
            "Book name: ";

    public static final String chapterSummaryAnalysis = "\nBook summary & Analysis: ";

    public static final String customerInfoPrefix = "\n<customer information>\n" +
            "- Genre preference: ";

    public static final String customerInfoSuffix = "\nNow recommend 5 songs for this customer.\n" +
            "Print out just JSON.";
}

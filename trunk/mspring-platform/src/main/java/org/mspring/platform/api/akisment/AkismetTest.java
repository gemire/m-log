/**
 * 
 */
package org.mspring.platform.api.akisment;

/**
 * @author Gao Youbo
 * @since 2013-5-6
 * @description
 * @TODO
 */
public class AkismetTest {
    public static void main(String[] args) throws Exception {

        String key = "ee9fe77d047c";
        String blog = "http://www.mspring.org";

        Akismet akismet = new Akismet(key, blog);

        System.out.println("API key verified: " + akismet.verifyAPIKey());

        // akismet.commentCheck(ipAddress, userAgent, referrer, permalink,
        // commentType, author, authorEmail, authorURL, commentContent, null);

        System.out.println("Testing comment spam 1: " + akismet.commentCheck("0.0.0.1", "Mozilla/5.0 (...) Gecko/20051111 Firefox/1.5", "cialis", "http://www.foo.com", Akismet.COMMENT_TYPE_COMMENT, "viagra-test-123", "viagra-test-123", "viagra-test-123", "VIAGRA! LOTS OF VIAGRA! XXX SEX CIALIS IS GREAT! Viagra Canada Viagra Epi How To Stop Viagra <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> Spam Seizures And Viagra\n" + "Generic Viagra Viagra Discount. Guaranteed Cheapest Viagra pulmonary ...<a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a>" + "<a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a>", null));
        System.out.println("Testing comment spam 2: " + akismet.commentCheck("0.0.0.1", "Mozilla/5.0 (...) Gecko/20051111 Firefox/1.5", "cialis", "http://www.foo.com", Akismet.COMMENT_TYPE_COMMENT, "Bernie Mac", "foo@bar.com", "http://www.cialis.com", "fuck the XXX cialias", null));
        System.out.println("Testing comment spam 3: " + akismet.commentCheck("127.0.0.1", "Mozilla/5.0 (...) Gecko/20051111 Firefox/1.5", "", "", "", "", "", "", "VIAGRA! LOTS OF VIAGRA!", null));
        System.out.println("Testing comment spam 4: " + akismet.commentCheck("x.y.z.w", "XXX", "", "", "", "", "", "", "VIAGRA! LOTS OF VIAGRA!", null));
        System.out.println("Testing comment spam 5: " + akismet.commentCheck("x.y.z.w", "XXX", "", "", "", "viagra-test-123", "", "", "VIAGRA! LOTS OF VIAGRA!", null));
        System.out.println("Testing comment spam 6: " + akismet.commentCheck("viagra-test-123", "viagra-test-123", "viagra-test-123", "viagra-test-123", Akismet.COMMENT_TYPE_COMMENT, "viagra-test-123", "viagra-test-123", "viagra-test-123", "viagra-test-123", null));
        System.out.println("Testing comment spam 7: " + akismet.commentCheck("127.0.0.1", "", "", "", Akismet.COMMENT_TYPE_COMMENT, "viagra-test-123", "", "", "VIAGRA! LOTS OF VIAGRA!", null));
        System.out.println("Testing comment spam 8: " + akismet.commentCheck("127.0.0.1", "", "", "", Akismet.COMMENT_TYPE_COMMENT, "viagra-test-123", "", "", "Viagara! " + "VIAGRA! LOTS OF VIAGRA! XXX SEX CIALIS IS GREAT! Viagra Canada Viagra Epi How To Stop Viagra <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> Spam Seizures And Viagra\n" + "Generic Viagra Viagra Discount. Guaranteed Cheapest Viagra pulmonary ...<a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a>" + "<a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a>", null));
        System.out.println("Testing comment spam 9: " + akismet.commentCheck("127.0.0.1", "", "", "", Akismet.COMMENT_TYPE_TRACKBACK, "viagra-test-123", "", "", "Viagara! " + "VIAGRA! LOTS OF VIAGRA! XXX SEX CIALIS IS GREAT! Viagra Canada Viagra Epi How To Stop Viagra <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> Spam Seizures And Viagra\n" + "Generic Viagra Viagra Discount. Guaranteed Cheapest Viagra pulmonary ...<a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a>" + "<a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a> <a href=\"http://how-to-stop-viagra-cialis-spam.plantexpansion.org/\">Cialis</a>", null));
    }
}

package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=567 lang=java
 *
 * [567] 字符串的排列
 *
 * https://leetcode-cn.com/problems/permutation-in-string/description/
 *
 * algorithms
 * Medium (33.26%)
 * Likes:    86
 * Dislikes: 0
 * Total Accepted:    15K
 * Total Submissions: 44.4K
 * Testcase Example:  '"ab"\n"eidbaooo"'
 *
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 * 注意：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */

import com.tukeping.tools.annotation.Cost;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * two-pointers | sliding-window
 *
 * microsoft
 *
 * @author tukeping
 * @date 2020/2/10
 **/
public class LeetCode567 {

    /**
     * 注意：
     * 输入的字符串只包含小写字母
     * 两个字符串的长度都在 [1, 10,000] 之间
     *
     * 103/103 cases passed (1045 ms)
     * Your runtime beats 5.9 % of java submissions
     * Your memory usage beats 5.1 % of java submissions (50.8 MB)
     */
    @Cost
    public boolean checkInclusion(String s1, String s2) {
        int p2 = s1.length();
        char[] s1Chars = s1.toCharArray();

        for (int p1 = 0; p1 <= s2.length() - s1.length(); p1++, p2++) {
            if (equalV4(s1Chars, s2, p1, p2)) return true;
        }

        return false;
    }

    private boolean equalV4(char[] s1Chars, String s2, int p1, int p2) {
        Arrays.sort(s1Chars);
        char[] s2Chars = s2.substring(p1, p2).toCharArray();
        Arrays.sort(s2Chars);
        String a = String.valueOf(s1Chars);
        String b = String.valueOf(s2Chars);
        return a.equals(b);
    }

    private boolean equalV3(String s1, String s2, int p1, int p2) {
        int h1 = s1.hashCode();
        int h2 = s2.substring(p1, p2).hashCode();
        System.out.println(s1 + ", h1=" + h1 + " | " + s2.substring(p1, p2) + ", h2=" + h2);
        return (h1 == h2);
    }

    private boolean equalV2(char[] s1Chars, String s2, int p1, int p2) {
        int a = 0, b = 0;
        for (char s1Char : s1Chars) {
            a += s1Char * 31;
        }
        for (int i = p1; i < p2; i++) {
            b += s2.charAt(i) * 31;
        }
        System.out.println(String.valueOf(s1Chars) + ", a=" + a + " | " + s2.substring(p1, p2) + ", b=" + b);
        return (a == b);
    }

    private boolean equal(char[] s1Chars, String s2, int p1, int p2) {
        String tmp = s2.substring(p1, p2);
        List<Character> list = new ArrayList<>(s1Chars.length);
        for (char s1Char : s1Chars) {
            list.add(s1Char);
        }
        for (int k = 0; k < tmp.length(); k++) {
            Character target = tmp.charAt(k);
            if (!list.remove(target)) return false;
        }
        return true;
    }

    /**
     * 输入: s1 = "ab" s2 = "eidbaooo"
     * 输出: True
     * 解释: s2 包含 s1 的排列之一 ("ba").
     */
    @Test
    public void test1() {
        boolean b = checkInclusion("ab", "eidbaooo");
        assertTrue(b);
    }

    /**
     * 输入: s1= "ab" s2 = "eidboaoo"
     * 输出: False
     */
    @Test
    public void test2() {
        boolean b = checkInclusion("ab", "eidboaoo");
        assertFalse(b);
    }

    /**
     * 输入: s1 = "adc", s2 = "dcda"
     * 输出: true
     */
    @Test
    public void test3() {
        boolean b = checkInclusion("adc", "dcda");
        assertTrue(b);
    }

    /**
     * 输入: s1 = "abc", s2 = "bbbca"
     * 输出: true
     */
    @Test
    public void test4() {
        boolean b = checkInclusion("abc", "bbbca");
        assertTrue(b);
    }

    /**
     *
     * 输入: s1 = "flxvrcznjvpetwlglbxwjudtqpzqvlnezneorrzorbvcmewanlepafgsrrrnpbephanazfbxrffjpyyfgebnrnezpgbdzgpnpueusqmazuimfswqkcckovejskabxenbcaazsvloswwkeodbqwhvxuvolckoqbbnmylzdykqwihipfbuwqdjtsnprhvbvskjuqwpavovgtwldzlndpwmtapvuwbshlnzftqvikeugeesftjjfqxqckzcmckerqvkfgmutzfspubcmqdxgibkcctysaacjftxofhsyfvzubtlespxdphkcoexblqsywiaewxrypjltlyuktgcisryrodgwvmvnhmhkxvlxwkeuicjolldhvzjbugqbrprkkrptqpvolwbugjviwtewtfcojmeomitugthftnerwxidotaagigjegcqnvnntqaqzhutvwvyhslwxecgnpkbvqcooqyhwfkzigvwvixvthypyxwkwmxljljewnnsjopbgjfeumiafqbnhuusnwuogqobcaurezmvlsekvoxhmlwjvssrtqqhhcoscumxztetoxorhwyypymamovyeupopsxleapzyrwpuovvscxgghurnyabzpgrhjxsfaijsrcgnxafozqzkxzuprajukehkveqoopkbacynmabxkqawbvrtwbycmscktmhrtqzovvuaiaufnjkedpuwmasoxfcupizsiahnqofrqtwiluabwqwgyoeyrvkckqhozzxwqljwdrfcvhgmxgnasznkbxtvsjcunfzlpotxxsnfzuwjzrbvlazfvfobiukbcwqtjspztcsyscfasoauiofidyghfcsprdfzoavbtogicxnqliknlmiktdznjlmzczdtcinkiownvghvtdqfuigzfsumxbczwornoryzaxxvhiwngkconhdullpvkknjaioxwirycxwzxhhvujyliatmygomyisezkekotaxhhgviyonctjcqxxratadwfubdglijbgfvyloeeorwgrzxlhrexoffiamvdvzuujscreombfwukuudpgahcixvbczupnhawfdzfuiuefbnhevggxejbmbqgwxdcjfoxpmstyignzleofrojkxdotjzbgzyacguvmpgbfiemcmgswfzheosyjykvjsxmegdqtsedqxwhizdrtxqfxngqiiqhdqnkucnvlajdqsxthauceyfdxykklduhpfmswacvfcsyoubdrkqvhgmmrgjccdzoojrncgvapuejthnhkouupbvkvynfupbswyybwwiowdrsbpgfdqjyyjhkchhfllhpvnalptuxbpamxowxsragkazpftugfbxsrpzheymxdzisjguptpqsarhcfmhitdijcsdjtbjvciltewuxzckxnomjxzyimkpngwnryirysnihsorufktzhudantlkwpkgvgyannrgjhesrlefdwmkduhlypuivofvpdlzsrateylguvjrgmmpijxrlmcdqljkosrpjslxhpnvpaokqjkzxewjqxuerjyiitblgxpyfkufzuxwaxjougwiycsrwkyydzodmovuweeirbvuhvagqyjuvxchixdeplbmqepstpigawwljxepceapijvbqovlpwykrpmrqfacnfhbwlmisiuvwyijosxkuogtqpnfmqzoynsgfobzzasrwyazkirlsiiysjwysiqedfocdzzyhodvmhxkwroqxqjwfdlgmxlosbcqsgwnrdzjzwlechqalhqucneolyiypgvbnksjmhgildwbirijufnmsncdsakvswmwhzyelfndxxuvpgaeeibtchnxtbqqwqlrnaudohtiqzwlukrhjyhwvzmletyvndyikrpihpablsriolffyfjnopvdookfaccshcanohnjpafoxeszlhzgkwsdbuhrcqysmgwivobhlpiluzqrubblfvjxprjetvaanjxsloekhomfuysdyuyqorcmcjahvcjqkgmubbsfjfvgskbwkoubiremtdoxxztwvzikfumvdewiybnhsdvqaxvicfcokukryovhcynkqzydteyekflfbclzenbucqtzsgnmpemlfxaihaceoeewykkyepswtoxscqneytxctegnhlmvzvpryjmzvwcnoixiszxksjvhktroafqbwvfegjyvolzwqlmbfeoisqjirnprchgtujddwkvvneznqzmsghpokfhziuegdpieqlpfqalfxqbzrpdoqbujwrplehdacigxnieatlhftvgfqssvunkpoqnntybqaankwywnfgnzfkqayzaunlbqvhrzijpxsjyjmuiqhtfmazkvsqzitrwbawfxbsoqiqdtxzciypqurzdoqhhcnokzbuyvuldwkjcbrlcxagrtfjxxqkmisqzsimvnssmuhxwscqtlqplhlalhntclgakvbvtcrrzzesxcrurpqkrrmffwdinnlebzfradplhapmybwfdfdohhonajvyzgosfqrmfjoujmubrnocohzudbjniswmfqolsyigwteuifmasbubrzrjijhkcdxqgqixeoddrhtpmemigpormizdizhdxnazfiqqrywjselyzekpjtpucrtopokqbupebdvcjzazfeyyyhkuagfsmyrnmkewkaxaznwcdzusfajvamlobazycdjcnqxpgwkokvqejkkaumbksrotynsmwddfmykqkwfzxaspoapvbunvmmtllrzmldohpkrkoyotsycrtbtplxxsuvzrkqgqalelevsrdxtbzfpueasccuabjrhxeopzjgyoouzydyvzxnqqefjrwldltfjgtjafwumxsthgvzqcjjtotdbrokenyrfsjiofsxmlkkkmdsjwvqejdnvewslkkhuclfbvucwxwwgobszgmkewcscaupxisowechkjfylkeffpsaenxkhcogiyqmsgpvtdlowfssmolidndirzkcobmcjcyqymhqdgnckakkyjuzpdwgvfwoitfavpycfoocwewxyvgshfpydbhhgkauprdexcnfvxxqymzugwhbxfidemmbeznjneqmeuzrxkyvbyetangjfetehnlzhybsaigwljsktxdcqkpkbvwnlcwrpofcxxethfspabgrmihcgpmsamptaydawwznlcvmqgdalcwxkdnphxrhgkrjtlfbdxtotjrgimshvzljnvjbywuylhwfadtvqkjjevvvbrumdsbcnexsjzlzzmydghsrxerlttsoujuzhdixkrutvzevnealyzfzgzjdzvapyrsuwyplsvufcpbrtadhoyncxdpcfrzcgukjnuvqhrnhzaimsmaekhmutymgvyjikaaibnmvbmgolfuwmopcpiusimdzsqkmeneqbesjlkrsfieypkkztttiqfcnquiknsgurcoertbeehxgwquvkzvwdapsxznannzkqtukcfwafzyxedjnbdrvbtjkqufovtnbmhrxbofqzljltfkinklrsuyckmufosfoncfpvkoqketlcbkvkoxobbncnoiwedzfgvyqtfwlozewaystejiloauopqctjeitjwyqbxdhtogvqmsvplxvmodtcbpmwyqjcspjwxqatwpsjhngsuanvqjtntnuqplajsevmyqschmgwxfcdkynf"
     *      s2 = "yaalpjvqjqqczxgxfyinagfcuvkgoqzeezcgymzlinnibplbkwifxzjazrbaabqstpncuoeuricouwpyqltwwkkojtyqarkuwpmlxchvuwamjzvsqwnzhkneopswdtmvysjumpilfopzeiccnkxjpvdhhqiygpcrnmsarpanfrbdhxpmhuoujxtfyskijevauvojuzzaxxmhtwrtvcjzccwclytwojsbkvbekjdbpmjehgrwyttjakluwhbexeewjfiyjcoqtjehrstjetyyubuhauoqkwojgekglomfrwgknjwbovlnbjqxpgvaqlmhpnuiixtwcxmmwgflkhsclpsgysvuygqswbwblrmvsmyumeylcxufqgrbgrzqelzrsxslomyimfrrvjwkuaezjisnddsqsnonunlqctjlaosplbiyhaptmsjhfeswczhioyclfryrnkkujlrocpzfuiedxvzmmvttixqmaninfadgbogflcukuontwcfpwjargnhyelrlkuziojhfaoyplbtojadvndxihvdhhinjtqetxcgseheunstynawujjipowdnucjzgujzwrwjgiycafkkwzemwpzjxfecefynvoyekfdkgdddyryhlxgtlozjcwyrkokxdlsdsemamcspzriuzlqujsogmgwikksvakmadzwjijhlqsawtrskrqfwunqxzscjiewtibmctcxezflxvrcznjvpetwlglbxwjudtqpzqvlnezneorrzorbvcmewanlepafgsrrrnpbephanazfbxrffjpyyfgebnrnezpgbdzgpnpueusqmazuimfswqkcckovejskabxenbcaazsvloswwkeodbqwhvxuvolckoqbbnmylzdykqwihipfbuwqdjtsnprhvbvskjuqwpavovgtwldzlndpwmtapvuwbshlnzftqvikeugeesftjjfqxqckzcmckerqvkfgmutzfspubcmqdxgibkcctysaacjftzofhsyfvzubtlespxdpukcoexblqsywiaewxrypjltlyuktgcisryrodgwvmvnhmhkxvlxwmeuicjolldhvzjbugqbrprkkrptqpvolwbugjviwtewtfcojmeomitugthftnerwxidotaagigjegcqnvnntqaqzhttvwvyhslwxecgnpkbvqcooqyhwfkzigvwvyxvthypyxwkwmxojljewnnsjopbgjfeumiafqbnhuusnwuogqobcaurezmvlsekvoxhmlwjvssrtqqhhcoscumxztetoxorhwyypymamovyehpopsxleapzyrwpuovvscxgghurnyabzpgrhjxsfaijsqcgnxafozqzkxzuprajukehkveqoopkbacynmabxkqawbvrtwbycmscktmhrtqzovvuaiaufnjkedpuwmasoxfcupizsiahnqofrqtwiluabwqwgyoeyrvkckqhozzxwqljwdrfcvhgmxgnasznkbxtvsjcunfzlpotxxsnfzuwjzrbvlazfvfobiukbcwqtjspztcsyscfasoauiofidyghfcsprdfzoavbtogicxnqliknlmiktdznjlmzczdtcinkiownvghvtdqfuigzfsumxbczwornoryzaxxvhiwngkconhdullpvkknjaioxwirycxwzxhhvrjyliatmygomyisezkekotaxhhgviyonctjcqxxratadwfubdglijbgfvyloeeorwgrzxlhrexoffiamvdvzuujscreombfwukuudpgahcixvbczupnhawfdzfuiuefbnhevggxejbmbqgwxdcjfoxpmstyignzleofrojkxdotjzbgzyacguvmpgbfiemcmgswfzheosyjykvjsxmegdqtsedqxwhixdrtxqfxngqiiqhdqnkucnvlajdqsxthauceyfdxykklduhpfmswacvfcsyoubdrkqvhgmmrgjccdzoojrncgvapuejthnhkouupbvkvynfupbswyybwwiowdrsbpgfdqjyyjhkchhfllhpvnalptuxbpamxowxsragkazpftugfbxsrpzheymxdzisjguptpqsarhcfmhitdijcsdjtbjvciltewuxzckxnomjxzyimkpngwnryirysnihsooufktzhudantlkwpkgvgyannrgjhesrlefdwmkduhlywuivofvpdlzsrateylguvjrgmmpijxrlmcdqljklsrpjslxhpnvpaokqjkzxewjqouerjyiitblqxpyfkufzuxwaxjougwiycsrwkyydzodmovuweeirbvuhvagqyjuvxchixdeplbmqepstpigawwljxepceapijvbqovlpwykrpmrqfacnfhbwlmisiuvwyijosxkuogtqpnfmqzoynsgfobzzasrwyazkirlsiiysjwysiqedfocdzzyhodvmhxkwroqxqjwfdlgmxlosbcqsgwnrdzjzwlechgalhqdcneolyiypgvbnksjmhgildwbirijufnmsncdsakvswmwhzyelfndxxuvpgaeeibtchnxtbqqwqlrnaudohtiqzwlukrhjyhwvzmletyvndyikrpihpablsriolffyfjnopvdookfaccshcanohnjpafoxeszlhzgkwsdbuhrcqysmgwivobhlpiluzqrubblfvjxprjetvaanjxsloekhomfuysdyuyqorcmcjahvcjqkgmubbsfjfvgskbwkoubiremtdoxxztwvzikfumvdewiybnhsdvqaxvicfcokukryovhcynkqzydteyekflfbclzenbucqtzsgnmpemlfxaihaceoeewykkyepswtoxscqneytxctegnhlmvzvpuyjmzvwcnoixiszxksjvhktroafqbwvfegjyvolzwqlmbfeoisqjirnprchgtujddwkvvneznqzmsghpokfhziuegdpieqlpfqalfxqbzrpdoqbujwrplehdacigxnieaulhftvgfqssvunkpoqnntybqaankwywnfgnzfkqayzaunlbqvhrzijpxsjyjmuiqhtfmazkvsqzitrpbawfxbsoqiqdtxzciyqqurzdoqhhcnokzbuyvuldwkjcbrlcxagrtfjxxqkmisqzsimvnsskuhxwscqtlqplhlalhntclgakvbvtcrrzzesxcrurpqkrrmffwdinnlebzfradplhapmybwfdfdohhxnajvyzgosfqrmfjoujmubrnocohzudbjniswmfrolsyigwteuifmasbubrzrjijhkcdxqgqixeoddrhtpmemigpormizdizhdxnazfiqqriwjselyzekpjtpucrtopokqbupebdvcjzazfeyyyhkuagfsmyrnmkewkaxaznwcdzusfajvamlobazycdjcnqxpgwkokvqejkkaumbksrotynsmwddfmykqkwfzxaspoapvbunvmmtllrzmldohpkrkoyotsycrtbtplxxsuvzrkqgqalelevsrdxtbzfpueasccuabjrhxeopzjgyoruzydyvzxnqqefjrwldltfjgtjafwumxsthgvzqcjjtotdbrokenyrfsjiofsxmlkkkmdsjwvqejdnvewslkkhuclfbvucwxwwgobszgmkewcscaupxisowechkjfylkeffpsaenxkhcogiyqmsgpvtdlowfssmolidndirzkcobmcjcyqymhqugnckakkyjuzpdwgvfwoitfavpycfoocwewxyvgshfpydbhhgkauprdexcnfvxxqymzugwhbxfidemmbeznjneqmeuzrxkyvbyetangjfetehnlzhybsaigwldsktxdcqkpkbvwnlcwrpofcxxethfspabgrmihcgpmsamptaydawwznlcvmqgdalcwxkdnphxrhgkrjtlfbdxtotjrgimshvzljnvjbywuylhwfadtvqkjjevvvbrumdsbcnexsjzlzzmydghsrxerlttsoujuzhdixkrutvzevnealyzfzgzjdzvapyrsuwyplsvufcpbrtajhoyncxdpcfrzcgukjnuvqhrnhzaimsmaekhmutymgvyjikaaibnmvbmgolfuwmopcpiusimdzsqkmeneqbesjlkrsfieypkkztttiqfcnquiknsgurcoertbeehxgwquvkzvwdapsxznannzkqtukcfwafzyxedjnbdrvbtjkqufovtnbmhrxbofqzljltfkinklrsuyckmufosfoncfpvkoqketlcbkvkoxobbncnoiwedzfgvyqtfwlozewaystejiloauopqctjeitjwyqbxdhtogvqmsvplxvmodtcbpmwyqjcspjwxqatwpsjhngsuanvqjtntnuqplajsevmypschmgwxfcdkynfzlhsqqwdcgdbxpezgdkpjcoqtaddqfsahlphsqxofdfevbncnjawmmgth"
     * 输出: false
     */
    @Test
    public void test5() {
        String s1 = "flxvrcznjvpetwlglbxwjudtqpzqvlnezneorrzorbvcmewanlepafgsrrrnpbephanazfbxrffjpyyfgebnrnezpgbdzgpnpueusqmazuimfswqkcckovejskabxenbcaazsvloswwkeodbqwhvxuvolckoqbbnmylzdykqwihipfbuwqdjtsnprhvbvskjuqwpavovgtwldzlndpwmtapvuwbshlnzftqvikeugeesftjjfqxqckzcmckerqvkfgmutzfspubcmqdxgibkcctysaacjftxofhsyfvzubtlespxdphkcoexblqsywiaewxrypjltlyuktgcisryrodgwvmvnhmhkxvlxwkeuicjolldhvzjbugqbrprkkrptqpvolwbugjviwtewtfcojmeomitugthftnerwxidotaagigjegcqnvnntqaqzhutvwvyhslwxecgnpkbvqcooqyhwfkzigvwvixvthypyxwkwmxljljewnnsjopbgjfeumiafqbnhuusnwuogqobcaurezmvlsekvoxhmlwjvssrtqqhhcoscumxztetoxorhwyypymamovyeupopsxleapzyrwpuovvscxgghurnyabzpgrhjxsfaijsrcgnxafozqzkxzuprajukehkveqoopkbacynmabxkqawbvrtwbycmscktmhrtqzovvuaiaufnjkedpuwmasoxfcupizsiahnqofrqtwiluabwqwgyoeyrvkckqhozzxwqljwdrfcvhgmxgnasznkbxtvsjcunfzlpotxxsnfzuwjzrbvlazfvfobiukbcwqtjspztcsyscfasoauiofidyghfcsprdfzoavbtogicxnqliknlmiktdznjlmzczdtcinkiownvghvtdqfuigzfsumxbczwornoryzaxxvhiwngkconhdullpvkknjaioxwirycxwzxhhvujyliatmygomyisezkekotaxhhgviyonctjcqxxratadwfubdglijbgfvyloeeorwgrzxlhrexoffiamvdvzuujscreombfwukuudpgahcixvbczupnhawfdzfuiuefbnhevggxejbmbqgwxdcjfoxpmstyignzleofrojkxdotjzbgzyacguvmpgbfiemcmgswfzheosyjykvjsxmegdqtsedqxwhizdrtxqfxngqiiqhdqnkucnvlajdqsxthauceyfdxykklduhpfmswacvfcsyoubdrkqvhgmmrgjccdzoojrncgvapuejthnhkouupbvkvynfupbswyybwwiowdrsbpgfdqjyyjhkchhfllhpvnalptuxbpamxowxsragkazpftugfbxsrpzheymxdzisjguptpqsarhcfmhitdijcsdjtbjvciltewuxzckxnomjxzyimkpngwnryirysnihsorufktzhudantlkwpkgvgyannrgjhesrlefdwmkduhlypuivofvpdlzsrateylguvjrgmmpijxrlmcdqljkosrpjslxhpnvpaokqjkzxewjqxuerjyiitblgxpyfkufzuxwaxjougwiycsrwkyydzodmovuweeirbvuhvagqyjuvxchixdeplbmqepstpigawwljxepceapijvbqovlpwykrpmrqfacnfhbwlmisiuvwyijosxkuogtqpnfmqzoynsgfobzzasrwyazkirlsiiysjwysiqedfocdzzyhodvmhxkwroqxqjwfdlgmxlosbcqsgwnrdzjzwlechqalhqucneolyiypgvbnksjmhgildwbirijufnmsncdsakvswmwhzyelfndxxuvpgaeeibtchnxtbqqwqlrnaudohtiqzwlukrhjyhwvzmletyvndyikrpihpablsriolffyfjnopvdookfaccshcanohnjpafoxeszlhzgkwsdbuhrcqysmgwivobhlpiluzqrubblfvjxprjetvaanjxsloekhomfuysdyuyqorcmcjahvcjqkgmubbsfjfvgskbwkoubiremtdoxxztwvzikfumvdewiybnhsdvqaxvicfcokukryovhcynkqzydteyekflfbclzenbucqtzsgnmpemlfxaihaceoeewykkyepswtoxscqneytxctegnhlmvzvpryjmzvwcnoixiszxksjvhktroafqbwvfegjyvolzwqlmbfeoisqjirnprchgtujddwkvvneznqzmsghpokfhziuegdpieqlpfqalfxqbzrpdoqbujwrplehdacigxnieatlhftvgfqssvunkpoqnntybqaankwywnfgnzfkqayzaunlbqvhrzijpxsjyjmuiqhtfmazkvsqzitrwbawfxbsoqiqdtxzciypqurzdoqhhcnokzbuyvuldwkjcbrlcxagrtfjxxqkmisqzsimvnssmuhxwscqtlqplhlalhntclgakvbvtcrrzzesxcrurpqkrrmffwdinnlebzfradplhapmybwfdfdohhonajvyzgosfqrmfjoujmubrnocohzudbjniswmfqolsyigwteuifmasbubrzrjijhkcdxqgqixeoddrhtpmemigpormizdizhdxnazfiqqrywjselyzekpjtpucrtopokqbupebdvcjzazfeyyyhkuagfsmyrnmkewkaxaznwcdzusfajvamlobazycdjcnqxpgwkokvqejkkaumbksrotynsmwddfmykqkwfzxaspoapvbunvmmtllrzmldohpkrkoyotsycrtbtplxxsuvzrkqgqalelevsrdxtbzfpueasccuabjrhxeopzjgyoouzydyvzxnqqefjrwldltfjgtjafwumxsthgvzqcjjtotdbrokenyrfsjiofsxmlkkkmdsjwvqejdnvewslkkhuclfbvucwxwwgobszgmkewcscaupxisowechkjfylkeffpsaenxkhcogiyqmsgpvtdlowfssmolidndirzkcobmcjcyqymhqdgnckakkyjuzpdwgvfwoitfavpycfoocwewxyvgshfpydbhhgkauprdexcnfvxxqymzugwhbxfidemmbeznjneqmeuzrxkyvbyetangjfetehnlzhybsaigwljsktxdcqkpkbvwnlcwrpofcxxethfspabgrmihcgpmsamptaydawwznlcvmqgdalcwxkdnphxrhgkrjtlfbdxtotjrgimshvzljnvjbywuylhwfadtvqkjjevvvbrumdsbcnexsjzlzzmydghsrxerlttsoujuzhdixkrutvzevnealyzfzgzjdzvapyrsuwyplsvufcpbrtadhoyncxdpcfrzcgukjnuvqhrnhzaimsmaekhmutymgvyjikaaibnmvbmgolfuwmopcpiusimdzsqkmeneqbesjlkrsfieypkkztttiqfcnquiknsgurcoertbeehxgwquvkzvwdapsxznannzkqtukcfwafzyxedjnbdrvbtjkqufovtnbmhrxbofqzljltfkinklrsuyckmufosfoncfpvkoqketlcbkvkoxobbncnoiwedzfgvyqtfwlozewaystejiloauopqctjeitjwyqbxdhtogvqmsvplxvmodtcbpmwyqjcspjwxqatwpsjhngsuanvqjtntnuqplajsevmyqschmgwxfcdkynf";
        String s2 = "yaalpjvqjqqczxgxfyinagfcuvkgoqzeezcgymzlinnibplbkwifxzjazrbaabqstpncuoeuricouwpyqltwwkkojtyqarkuwpmlxchvuwamjzvsqwnzhkneopswdtmvysjumpilfopzeiccnkxjpvdhhqiygpcrnmsarpanfrbdhxpmhuoujxtfyskijevauvojuzzaxxmhtwrtvcjzccwclytwojsbkvbekjdbpmjehgrwyttjakluwhbexeewjfiyjcoqtjehrstjetyyubuhauoqkwojgekglomfrwgknjwbovlnbjqxpgvaqlmhpnuiixtwcxmmwgflkhsclpsgysvuygqswbwblrmvsmyumeylcxufqgrbgrzqelzrsxslomyimfrrvjwkuaezjisnddsqsnonunlqctjlaosplbiyhaptmsjhfeswczhioyclfryrnkkujlrocpzfuiedxvzmmvttixqmaninfadgbogflcukuontwcfpwjargnhyelrlkuziojhfaoyplbtojadvndxihvdhhinjtqetxcgseheunstynawujjipowdnucjzgujzwrwjgiycafkkwzemwpzjxfecefynvoyekfdkgdddyryhlxgtlozjcwyrkokxdlsdsemamcspzriuzlqujsogmgwikksvakmadzwjijhlqsawtrskrqfwunqxzscjiewtibmctcxezflxvrcznjvpetwlglbxwjudtqpzqvlnezneorrzorbvcmewanlepafgsrrrnpbephanazfbxrffjpyyfgebnrnezpgbdzgpnpueusqmazuimfswqkcckovejskabxenbcaazsvloswwkeodbqwhvxuvolckoqbbnmylzdykqwihipfbuwqdjtsnprhvbvskjuqwpavovgtwldzlndpwmtapvuwbshlnzftqvikeugeesftjjfqxqckzcmckerqvkfgmutzfspubcmqdxgibkcctysaacjftzofhsyfvzubtlespxdpukcoexblqsywiaewxrypjltlyuktgcisryrodgwvmvnhmhkxvlxwmeuicjolldhvzjbugqbrprkkrptqpvolwbugjviwtewtfcojmeomitugthftnerwxidotaagigjegcqnvnntqaqzhttvwvyhslwxecgnpkbvqcooqyhwfkzigvwvyxvthypyxwkwmxojljewnnsjopbgjfeumiafqbnhuusnwuogqobcaurezmvlsekvoxhmlwjvssrtqqhhcoscumxztetoxorhwyypymamovyehpopsxleapzyrwpuovvscxgghurnyabzpgrhjxsfaijsqcgnxafozqzkxzuprajukehkveqoopkbacynmabxkqawbvrtwbycmscktmhrtqzovvuaiaufnjkedpuwmasoxfcupizsiahnqofrqtwiluabwqwgyoeyrvkckqhozzxwqljwdrfcvhgmxgnasznkbxtvsjcunfzlpotxxsnfzuwjzrbvlazfvfobiukbcwqtjspztcsyscfasoauiofidyghfcsprdfzoavbtogicxnqliknlmiktdznjlmzczdtcinkiownvghvtdqfuigzfsumxbczwornoryzaxxvhiwngkconhdullpvkknjaioxwirycxwzxhhvrjyliatmygomyisezkekotaxhhgviyonctjcqxxratadwfubdglijbgfvyloeeorwgrzxlhrexoffiamvdvzuujscreombfwukuudpgahcixvbczupnhawfdzfuiuefbnhevggxejbmbqgwxdcjfoxpmstyignzleofrojkxdotjzbgzyacguvmpgbfiemcmgswfzheosyjykvjsxmegdqtsedqxwhixdrtxqfxngqiiqhdqnkucnvlajdqsxthauceyfdxykklduhpfmswacvfcsyoubdrkqvhgmmrgjccdzoojrncgvapuejthnhkouupbvkvynfupbswyybwwiowdrsbpgfdqjyyjhkchhfllhpvnalptuxbpamxowxsragkazpftugfbxsrpzheymxdzisjguptpqsarhcfmhitdijcsdjtbjvciltewuxzckxnomjxzyimkpngwnryirysnihsooufktzhudantlkwpkgvgyannrgjhesrlefdwmkduhlywuivofvpdlzsrateylguvjrgmmpijxrlmcdqljklsrpjslxhpnvpaokqjkzxewjqouerjyiitblqxpyfkufzuxwaxjougwiycsrwkyydzodmovuweeirbvuhvagqyjuvxchixdeplbmqepstpigawwljxepceapijvbqovlpwykrpmrqfacnfhbwlmisiuvwyijosxkuogtqpnfmqzoynsgfobzzasrwyazkirlsiiysjwysiqedfocdzzyhodvmhxkwroqxqjwfdlgmxlosbcqsgwnrdzjzwlechgalhqdcneolyiypgvbnksjmhgildwbirijufnmsncdsakvswmwhzyelfndxxuvpgaeeibtchnxtbqqwqlrnaudohtiqzwlukrhjyhwvzmletyvndyikrpihpablsriolffyfjnopvdookfaccshcanohnjpafoxeszlhzgkwsdbuhrcqysmgwivobhlpiluzqrubblfvjxprjetvaanjxsloekhomfuysdyuyqorcmcjahvcjqkgmubbsfjfvgskbwkoubiremtdoxxztwvzikfumvdewiybnhsdvqaxvicfcokukryovhcynkqzydteyekflfbclzenbucqtzsgnmpemlfxaihaceoeewykkyepswtoxscqneytxctegnhlmvzvpuyjmzvwcnoixiszxksjvhktroafqbwvfegjyvolzwqlmbfeoisqjirnprchgtujddwkvvneznqzmsghpokfhziuegdpieqlpfqalfxqbzrpdoqbujwrplehdacigxnieaulhftvgfqssvunkpoqnntybqaankwywnfgnzfkqayzaunlbqvhrzijpxsjyjmuiqhtfmazkvsqzitrpbawfxbsoqiqdtxzciyqqurzdoqhhcnokzbuyvuldwkjcbrlcxagrtfjxxqkmisqzsimvnsskuhxwscqtlqplhlalhntclgakvbvtcrrzzesxcrurpqkrrmffwdinnlebzfradplhapmybwfdfdohhxnajvyzgosfqrmfjoujmubrnocohzudbjniswmfrolsyigwteuifmasbubrzrjijhkcdxqgqixeoddrhtpmemigpormizdizhdxnazfiqqriwjselyzekpjtpucrtopokqbupebdvcjzazfeyyyhkuagfsmyrnmkewkaxaznwcdzusfajvamlobazycdjcnqxpgwkokvqejkkaumbksrotynsmwddfmykqkwfzxaspoapvbunvmmtllrzmldohpkrkoyotsycrtbtplxxsuvzrkqgqalelevsrdxtbzfpueasccuabjrhxeopzjgyoruzydyvzxnqqefjrwldltfjgtjafwumxsthgvzqcjjtotdbrokenyrfsjiofsxmlkkkmdsjwvqejdnvewslkkhuclfbvucwxwwgobszgmkewcscaupxisowechkjfylkeffpsaenxkhcogiyqmsgpvtdlowfssmolidndirzkcobmcjcyqymhqugnckakkyjuzpdwgvfwoitfavpycfoocwewxyvgshfpydbhhgkauprdexcnfvxxqymzugwhbxfidemmbeznjneqmeuzrxkyvbyetangjfetehnlzhybsaigwldsktxdcqkpkbvwnlcwrpofcxxethfspabgrmihcgpmsamptaydawwznlcvmqgdalcwxkdnphxrhgkrjtlfbdxtotjrgimshvzljnvjbywuylhwfadtvqkjjevvvbrumdsbcnexsjzlzzmydghsrxerlttsoujuzhdixkrutvzevnealyzfzgzjdzvapyrsuwyplsvufcpbrtajhoyncxdpcfrzcgukjnuvqhrnhzaimsmaekhmutymgvyjikaaibnmvbmgolfuwmopcpiusimdzsqkmeneqbesjlkrsfieypkkztttiqfcnquiknsgurcoertbeehxgwquvkzvwdapsxznannzkqtukcfwafzyxedjnbdrvbtjkqufovtnbmhrxbofqzljltfkinklrsuyckmufosfoncfpvkoqketlcbkvkoxobbncnoiwedzfgvyqtfwlozewaystejiloauopqctjeitjwyqbxdhtogvqmsvplxvmodtcbpmwyqjcspjwxqatwpsjhngsuanvqjtntnuqplajsevmypschmgwxfcdkynfzlhsqqwdcgdbxpezgdkpjcoqtaddqfsahlphsqxofdfevbncnjawmmgth";
        boolean b = checkInclusion(s1, s2);
        assertTrue(b);
    }

    /**
     * 输入: s1 = "uhlqdzjmsmdzrgcjqdevltghvtjzkcckexesbldwjjarkjaocmwubzwqnuqikydqatbvokaxtbxakmrobpnuavjzctgjogmnbnjpvlmwlzrxutszuvtkrbxejyklaeqprhhcixtmcnmvvhqhuqjffvmjjycgrgkdrlxkabymcuhesisrqmyumkjqxfeydpbbjflkteblsyscmibgiqovrxpvbejmjaztimulmoclmjwbepasijdlwuvirzxxruoawcipmpbrekogzuctkjobzuhwiefvereuyjbxproizfipceidjaybvymiwpeuiqcatokgdeedufeczbkwcqqocfqqueyofkzjlshjhgkhavgltyzxdhkxddhyddgttfddofoqtmlsykffoffxfhgfcugtegtwvxtkkitogwkcgidpmbckbddialbloyswuntspzwqygllsnczknbtluooxqrzbefgpbldjeowditvnyertifubiuyyuoqkqcpvszrutjmuywyxkbwzfdvodkyzrbthoemktxedeuevzgwstdvfsskqusecqgymzzbohavgvtgrhxvvturrqxwtwgghbpnvftrqsnktgczshbdoabtptehehaoisrwzmbtvpzphzwivxauswemkkgqexxedlzwaxhuhbybnwldcpkbxalpcoymlhqqjyzhwrkukgvyuefvjargjkxnuerbywamzpbhottksicixcumvtypaogmtxjshajptmpicjmrwmyazqiihiecvufqoqthdmpflydwwcfqrnschgdrciweyfvxcpyebixanzixahhudrtrxtuhvfdyztapcabpfunjmmvhufsdkicqensiibrfjijvsxjdqzclcaiyoebiatmnmiufbtjnhschoxboojjagijficguwpluyyqosprjahcqdibeckzbxaezbiglakdwrcnmjanilmudvqivxnifrrbgmpyjfwujupzalhrhptaplxvsuoybzlyoyqdjcpgxmapxftbojsqwnaedfefufvvavpnsghylbqdnomkhbrraikxnmvancqdoihhtpksiqspaijzwtohbqtolxjysaiaylskijnkgmrppvyspslvvzeemcaniylpgjuxwtwzonjliuxtlyjjhogytdxeihzfamrqpsrirjrgruyfwlonybchhrejhktxewddffddqfesenfrjcujmvbtzgfigqmwhmbwtofsfuududneljnqagnlgzzwwdzfuxyegpdkbefqbidylgajptpnlyukptxhcpafqiphwgbuforjybejzgnnofztniulgdficdbotmnnjthzqxuxzmgoojklscosjfvkucwiuiifclvqwpxwjcyliwkfstuxdmqtwoaxcmchdewbkhjfonbrcstqnyhtpentvxcdotqbsshurypjzksguektjmtgbonbspsvhsxcrbdzeifogivhrybexhfcuprormirisrafdkcbrnqkrgamfvebykmjeljzrsmuwdlvbojjrarzwalcyxgndcyhfiegglrmmfhxzpwiougyhqnajsfkatzdnwyldbghvzknujzjsdsluoyexhnswlmdyjiimbvdqgukxznrampsasojqhdpjsmkicinaojylnaxjumqwvdsmuvogxdpccjdcebavjcpclkbbpejwnzmyqzbhgxnytmvhjepvkaugeofvziekkwhyjhonveuqotuedyhrskyjlnibwltuhssxvxwzrjvaekqkpaksgnkumffotelesbuwfxuyrrziktkuxzycsxyumafpqlnnwdbowjzzombnwnrcaxmpmywmvhnfcqdbgdznbcxsjfehnhgegctusleqwtojzvabyluilyyunzmslnbqgjrtwibnvyyzzfbjoqhfaokdkdlpsdyigglbvmmvsgnkigvdabwupgpdxtykfrvzoxeefuxyptzecpvhbfnkbbeuaytkwrstcwczqzbgjbobfaxsybvurphknedlepfuuzkpyzfodmkqdffbwyqnxgxnsthfgwbjibpfbnuoritrtgbkyjrcujgpuoweuobawgzllrrtcauxnyvvrqaacgdjhdpfgvuyusfjdawnovfpcalutebuclttnssqwueylqlkmwnujudawnxtyzbtgonyroppwexmubgeegecbpsafywiniyfoxqyrcwogjuslorfzyjoiifwrhggsodbuzqvzdxelquloyolmrushxzfiuzdojdjtogprubpfceekoouzwktmsmfzdcqkyubgasdxatuhbrlnlptmjsafplmlfntrjovrqkugldzvvnelinxvazfkjygnnfchmajqqdynpdkjsmbjysajeqegofqhakrcahngsiswjoitkodyygmumyngnsjuvvbackdkzfbcjzwokyxengdgxsgkrrpsrrhiigxanriytcjktjoouflfcxerivldvgbasowoawajqxnumrbhgiaspakmadtuvirwthbvppsgafteztgckcjttxzqfmxfcfumzcagyzenxcbhkyrkmwevqghfjioopnpcqkjfpceuztzhxsvcdclflkabglwqdfgofrbqbjmunmhivczndkunxapxwwjxieiwsncvtclvhtwtqiguysqkugmiuollhoixwnsmupcplmrxlqprwrdkqurhtncllueskaynopaekidvjgfqepjffmfezklllxmkezjwnnloxlvdulwvarifopnugajspzypdosmomdcpouozmyirppbftzpxipkvklttffcxqqisqfdeeuokphclytdfbtogmmrglfbcbtbhgrjmhyzthidybwqdywrryzunfmvuvjuvkdjcwyqwwoaotsmvicodnngxdwwxlkw"
     *      s2 = "lkrzcpwilhhuukffdtbajghpqrjsypwzkuviqhbxtnzlubuzyveujbnljkttzwmjdrukrzeswttdzjfvqrlfzztguavagwcugmmhxjdqpoagxemkvhfnhgclsejzfsfttiwvjmfqufqoprdbegoqoucaxyylxmwfbmkujckwnnskxvkedowmwvabmdkeeclvjubydnihymgrdzodajprsfaodwqovmgmzrvtsshlbkbthbheumgsknwqbdqohxkkbxjsmicwlujfpilukllevoleztzxpplwcwsngpbhugofynxnbhfkngxjfmovhzsxftjthzytgpnejbnfqbeowjanppgxkqnxxdkpakqvpkbefthbiemgwwifskvysbmbhuoheqrgqpyaziuiwwhhduzorzqggiffxxhdihzxdncvrlvzitiewgwlbgylufpianpekzhwlecdhyrubcttfruxmzfukiirciagaqchvluyjsmzvpahtrtaisuzrqagutlasdgbxvqyqhhxozzyxouxfrvikrfaawneoyyornaxbcizbrhtytwrljwazyklydnsviwfcfgrkawirkhoyhrbpppifbdnknkqdkzmbcfrmqkbsziarbwadssrliujdgwrxjoduyancvsabuqrmgyppjnkxvuthzquaxsbxkdsavvrgyxsklfizfsycyrnhymmbjcxvhlcxqpeatipzxdzfayuqhvudbpwoowcvbwpjgfiigrskssigdnuuhhfxrrcdvtmnsverwpnjefnzirghlxnizusztvjawchiecdgtsawogxdxjaewgiyabjywhhnkfbdrucihhxopxmxrxyslcumfsrurnsybwcebxrcqlmthilndrfwzvvsqirsqmryluwavjjlvdstefpxylinmxgmvlzjoewwpracbkixoapezvdsjoamarnsgzufzdgxteypomrqqkazsxrbvkmviisvanumskxkpzrrvmletqanboionifpdszyleowjagagglnamwmopfindniokvkydsnayaezdmjfkbrfwuxbaoxicnoxfakwjmvfchqtnifuwtswwvvxircuzuhdobdqkzmczenfptpihpzhorjlqpqudijfqwzikwnhczkualaxiuebctizkqtcrxkpemzugpfpbaybsflzsvwnlnysnwsnlrdmtcrdgwurgioobcwjroiqlbsdcvemzaqtffiliwerqdispranlcvdyemmyyogrwbxqqghxoqytjnrzrdioejilthxpzkjbhjztasjvknjoklgwhtegujxvkcykabablssobxsyrkcjjobmkkfbyhwkztpnlgljqzydluiskkegngrzrkebbmufhglaqslieeosbywehrgtkfugaxzdsxnlcfudkdqqgjtvtxifbhjznjibqvvukkpsjttsexpqbpampciqoyxhiaciqdrwlliogcmfcbcxcqzhvvkmjbpmqgaxumaubjebxoanitykumqugkflemefahqvppjirmehsiyzgeguwoycquwyyyilmwmazcrldnivlnroblupdljmeaqzkwyuiufcvlazrthrhzbuucpmsummwhdjeqlzmsderjdekpuxovgucwnnmxcrkkaqnhlobdzdtpkpqtmhihrngnrygazklcpayqkfplqtcjfxejtpownqadaqwitghcykhxagevcsehinojnyfzqgaxulavxrkxunynztsnrdeciqkyuthqgtytatpvagbqnvbknailnesqqmughpeiqqkgfxuhxwqwgfrfgauiuqyyhupwccoosyvvvqbndxcptlncdpircgxyhxujqnaamxoyggyszbtbidggomlxzfyqlwlzrtjheckedypwcmyugvexfpnvagoebfmfrjcxbzaopvwggxhozpljvzdfmdotvczdbzavacgalwgdbjcjtzdxkdgtxzojyeixgaxluddgpqzzmilruccxacqqfgvlhdwmqwmmoacyamjlamcjkrfugbmzfdgdgmywyjexnnhuqixgyorzjzkyarvnkqcfnsohfuhlqdzjmsmdzrgcjqdevltghvtjfkcckeiesbldwjjarkjaocpwubzwqnuqikydqatbvokaxtbxakmrobpnzavjzctgjogmnbnjpvlmwlzrxutszuvtkrbxejyklaeqprhhcixtmonmvvhqhuqjffvmjjycgrgkdrlxkabymcuhesisrqmbumkjqxfeydpbbjflkteblsyscmibgiqovrxmvbejmjaztimulmoclmjwbepasijdlwuvirzbxrucawcipmpxrekogcuctkjabzuhwiefvereeyjbxproizfipckidjaybvymiwpeuiqcatokgdeedufezzbkwcqqocfqquecofkzjlshjhgkhavgltyuxdhkxddhyddgttzddofhbtmlsykfcoffxfhgfcuntegtwvxfkkitogwkpgidtmbckbddialbloyswuvtspzwqygllsnczknbtluooxquzbefgpbldjeowdxtvnyertifubiuyyuoqkqcpvszrutjmuywyxabwzfdvodkyzrbthoemktxedeuevzgwstdvfsskqusecqgymzzbohavgvtgrhxvvturrqxwtwgghbpnvftrqsnktgczshbdoabtptehehaoisrwzmbtvpzphzwhvxauswemkkgqexnedlzwaxhuhbybnwldcpkbxalccoymlhqqjyzhwrkukgvyuefvjargjkxnuerbywamzpbhottksicixcumvtypaogmtxjshajptmpicjmrpmytzqiihiecvufqoqthdmwflydwwcfqrnschgdrciweyfvxcpyebixanzixahhudrtrxtuhvfdyzwapcabpfunjmmvhufsdkncqensiibrfjijvsxjdqzclcaiyoebiatmnmiufbtjnhschoxboojjagijficguwpluyyqosprjahcqdibeckzbxoezbiglakdwrcnmjanilmudvqivxnifrrbgmpyjfwujupzalhrhptiplxvsuoybzlyoyqdjcpgxmapxftbojsqwnaedfefufvvaipnsghylbqdnomkhbrraikxnmvancqdoihitpksiqspaijzwuohbqtolxjysaiayoskijnkgmrppvyspslvvzeemcanvylpgjuxwtwzonjliuxtlyjjhogytdaeiozfamrqpsrirjrgruyfwlonybchhrejhktxewddffddqfesenfrjcujmvbtzgfigqmwhmbwtofsfuududneljnqagnlgzzwwdzfuxyegpdkbeflyidylgajptpnlyukptxhcpafqiphwgbuforjybejzgnnofztniulgdficdbotmnnjthzqxuxzmgoojklscosjtvktcwiuiifcqvqwpxwjcyliwkfstuxdmztwoaxcmchdewbkhjfonbrcstqnyhtpentvxcdotqqsshurypjzksguektjmtgbonbspsvhsxcrbdzeifogivhrybexhfcupronmirisrafdknbrnqkrgamfvebykmjeljzrsmuwdlvbojjrarzwalcyxgndcyhfiegglrmmfhxqpwiougyhqnajsfkatzdnwyldbghvzknujzjsdsluoyexhnswlmdyjiimbvdqgukxznrampsasojqhdpjsmkicinkojylnaxjumqwvdsmuvupxdpcfjdcebavjcpclkbbgejwnzmyqzbhgxnytmvhjepvkaugeofvziekkwhyjhonveoqotuedyhrskyjlcibwltuhssxvxwzrjvaekqkpaksgnkumffotelesbuwfxuyrrziktkuxzycsxyumafpqlnnwdbowjzzombnwnrcaxmpmywmvhnfcqdbgdzgbcxsjfuhnhgegctusleqwtojzvabyluilyyunzmslnbqgjrtwibnvyyzzfbjoqhfaokdkdlpsdyigglbvmmvsgikigvdabwupgpdxtykfrvzoxeefuxyptzecpvhbsnkbbelaytkwrstcwczqzbgjbobfaxsybvurphknedlepfuuzkpyzfodmkqdffbwyqnxgxnsthfgwbjibpfjnuoritrtgbkyjrcujgpuoweuobawgzllrrtcauxnyvvrqaacadjhdpfgvuyusfjdawnovfpcalutebuclttnssqwueylqlkmwnujudawnxtyzbtgonyroppwexmubgeegecbpsafywiniyfoxqyrcwogjuslorfzyboiifwrhggfodbuzqvzdxelquloyolmrushxzfiuzdojdjtogpruxpfceekoouzwktmsmfzdcqkyubgasdxatuhbrlnlptmjsafplmlfntrjovrqkugldzvvrelinxvazfkjygnnfchmajqqdynpdkjsmbjysajeqegofqhakrcahngsiswjoixkodyygmumyngnsjuvvbackdkzfbcjzwoeyxengdgxsgkrrpsrrhiigxanriytyjktjoouflfcxerivldvgbasowoawajqxnumrbhgiaspakmadtuvirwthbvppsgafteztgckcjttxzqfmxfcfumzcagyzexxcbhkyrkmwevqghfjiuopnpcqkjfpceuztzhxsvcdclflkabglwqdfgofrbqbjmunmhivczndkunxapbwwjxieiwsncvpclvhtwtqiguysqkugmiuollhoixwnsmupcplmrxlqprordkqurhtncllueskaynopaekidvjgfqepjffmfezklllxmkezjwnnloxlvdultvarifopnugajspzypnwsmomdcpouozmygrppbftzpxipkvklttffcxqqisqfdeeuokphclytdfbtogmmrglfbcbtbhgrjmhyzthidybwqdywrryzrnfmnuvjuvkdjcwyqwwoaotsmvicodndgxdwwxlkwtbflcnulyjfptodffqvlxjkhvwdoonxanrwjqnbtbvzpsrfrpcrdealcyhjfdqsckbrpyeduwnllelbvrshdeiasmebfhwfiofddqmvnewsapvfgdeqltoreinprmnhrfzvsjkqjgohzpgekjcnzskbwpxkkdsirshozmpnpvsbmccxebhxlilcubgfwmvislgtzovotufddbuynsmcsefqydbeelnhxpbsdiwyfrnyqzmoyzcewelkxtcohyamcauvvwclzxsgtqnhiuilbqidqmpqxtqskrxtsbixruwhadfpfpvmhphlewrblojkcpdbmqiitviohofbjxzdgfkbotxhzxtahhipvbctlbwypkhkcmkvqkerhbpkefhztyosrkknppcfqbohfuogwxecxpxttbaboidbhacrhevtrmukakzkuqlwtugxhzljwtbvluaaskjvnpngsicuznwrpbfzhhidraqwenxvcnbnooqpjyqnidypuokvuyqftbnmpvwsenpcvvmnlonxyooiicqzwasbtoasxsmsddczxvknupxtlwoolyjytzzkmfvlzggwosjahjevbaspveqxqyxuvpprgjifakmostvgqtrrikymrgrameejhvbatmgzuvdeljiipbvgwolhorfxsgramkfpyfvopuxckhvsrhwgdfaauhpmsyqfbsevgwdynhypxhekpfzxxslkbqgclczlxgpvfoxfthrhaqkhqegmxrzsbtmstvcabovuwzsgondounxyrtutjpocrnzwmoctucklqwiyvvnzucemwzwapnmqjmvezkrbeaznhjijfzqyzounzosgcitlyhviyjiedyzxpzbhkojasegsvewoimcoajhiincnlztekigtcudtdytyxnorzmyghxcpuvljtjghqoqfxirmsistcmsiazlohaflkfawegkfowlpowpogggdvsrgfkzjlgtxcslqvkdrcpvexvhnuohjdmuqoyvsbyysvbgmvmldqbmcxnutdbftxtiaiihxudsucgzuipmxpyezvhexadlyabrgtukalafiqeczlbihmpbxyerdzsrisulxdfxsnwtolvlynrotowbvjuckrmywqomlxiwvltgvwkdcovvkzebtumcdpwdbwrnflbkqktnuzjpchwhpxbknfyvqljjqwpfzldyhzlpcuccyllvdaezcrznsbvomriadouenndwyxvclrcjpkoivxmjrwkqrlrijexvxhnpbmwkpvqpbkcqxydrwmpdzykefjjssbtotkvoitduesbfeiqfjijwqofledklqmkgssgieplevysqrluzqpavwliosrouzczdyhxhtjtzoudqptlqectrsphiyevuesqictudybuplshepjkjbtujcpxvobqzzxpgnwwvpenkllotcnlakylegkokkygqojivxhnlrpkwmuhcscoyykexlikaouocjgosenadwktjistlbkbjecepgknoljvvdzruwextgaaruunbiihinvsc"
     * 输出: false
     */
    @Test
    public void test6() {
        String s1 = "uhlqdzjmsmdzrgcjqdevltghvtjzkcckexesbldwjjarkjaocmwubzwqnuqikydqatbvokaxtbxakmrobpnuavjzctgjogmnbnjpvlmwlzrxutszuvtkrbxejyklaeqprhhcixtmcnmvvhqhuqjffvmjjycgrgkdrlxkabymcuhesisrqmyumkjqxfeydpbbjflkteblsyscmibgiqovrxpvbejmjaztimulmoclmjwbepasijdlwuvirzxxruoawcipmpbrekogzuctkjobzuhwiefvereuyjbxproizfipceidjaybvymiwpeuiqcatokgdeedufeczbkwcqqocfqqueyofkzjlshjhgkhavgltyzxdhkxddhyddgttfddofoqtmlsykffoffxfhgfcugtegtwvxtkkitogwkcgidpmbckbddialbloyswuntspzwqygllsnczknbtluooxqrzbefgpbldjeowditvnyertifubiuyyuoqkqcpvszrutjmuywyxkbwzfdvodkyzrbthoemktxedeuevzgwstdvfsskqusecqgymzzbohavgvtgrhxvvturrqxwtwgghbpnvftrqsnktgczshbdoabtptehehaoisrwzmbtvpzphzwivxauswemkkgqexxedlzwaxhuhbybnwldcpkbxalpcoymlhqqjyzhwrkukgvyuefvjargjkxnuerbywamzpbhottksicixcumvtypaogmtxjshajptmpicjmrwmyazqiihiecvufqoqthdmpflydwwcfqrnschgdrciweyfvxcpyebixanzixahhudrtrxtuhvfdyztapcabpfunjmmvhufsdkicqensiibrfjijvsxjdqzclcaiyoebiatmnmiufbtjnhschoxboojjagijficguwpluyyqosprjahcqdibeckzbxaezbiglakdwrcnmjanilmudvqivxnifrrbgmpyjfwujupzalhrhptaplxvsuoybzlyoyqdjcpgxmapxftbojsqwnaedfefufvvavpnsghylbqdnomkhbrraikxnmvancqdoihhtpksiqspaijzwtohbqtolxjysaiaylskijnkgmrppvyspslvvzeemcaniylpgjuxwtwzonjliuxtlyjjhogytdxeihzfamrqpsrirjrgruyfwlonybchhrejhktxewddffddqfesenfrjcujmvbtzgfigqmwhmbwtofsfuududneljnqagnlgzzwwdzfuxyegpdkbefqbidylgajptpnlyukptxhcpafqiphwgbuforjybejzgnnofztniulgdficdbotmnnjthzqxuxzmgoojklscosjfvkucwiuiifclvqwpxwjcyliwkfstuxdmqtwoaxcmchdewbkhjfonbrcstqnyhtpentvxcdotqbsshurypjzksguektjmtgbonbspsvhsxcrbdzeifogivhrybexhfcuprormirisrafdkcbrnqkrgamfvebykmjeljzrsmuwdlvbojjrarzwalcyxgndcyhfiegglrmmfhxzpwiougyhqnajsfkatzdnwyldbghvzknujzjsdsluoyexhnswlmdyjiimbvdqgukxznrampsasojqhdpjsmkicinaojylnaxjumqwvdsmuvogxdpccjdcebavjcpclkbbpejwnzmyqzbhgxnytmvhjepvkaugeofvziekkwhyjhonveuqotuedyhrskyjlnibwltuhssxvxwzrjvaekqkpaksgnkumffotelesbuwfxuyrrziktkuxzycsxyumafpqlnnwdbowjzzombnwnrcaxmpmywmvhnfcqdbgdznbcxsjfehnhgegctusleqwtojzvabyluilyyunzmslnbqgjrtwibnvyyzzfbjoqhfaokdkdlpsdyigglbvmmvsgnkigvdabwupgpdxtykfrvzoxeefuxyptzecpvhbfnkbbeuaytkwrstcwczqzbgjbobfaxsybvurphknedlepfuuzkpyzfodmkqdffbwyqnxgxnsthfgwbjibpfbnuoritrtgbkyjrcujgpuoweuobawgzllrrtcauxnyvvrqaacgdjhdpfgvuyusfjdawnovfpcalutebuclttnssqwueylqlkmwnujudawnxtyzbtgonyroppwexmubgeegecbpsafywiniyfoxqyrcwogjuslorfzyjoiifwrhggsodbuzqvzdxelquloyolmrushxzfiuzdojdjtogprubpfceekoouzwktmsmfzdcqkyubgasdxatuhbrlnlptmjsafplmlfntrjovrqkugldzvvnelinxvazfkjygnnfchmajqqdynpdkjsmbjysajeqegofqhakrcahngsiswjoitkodyygmumyngnsjuvvbackdkzfbcjzwokyxengdgxsgkrrpsrrhiigxanriytcjktjoouflfcxerivldvgbasowoawajqxnumrbhgiaspakmadtuvirwthbvppsgafteztgckcjttxzqfmxfcfumzcagyzenxcbhkyrkmwevqghfjioopnpcqkjfpceuztzhxsvcdclflkabglwqdfgofrbqbjmunmhivczndkunxapxwwjxieiwsncvtclvhtwtqiguysqkugmiuollhoixwnsmupcplmrxlqprwrdkqurhtncllueskaynopaekidvjgfqepjffmfezklllxmkezjwnnloxlvdulwvarifopnugajspzypdosmomdcpouozmyirppbftzpxipkvklttffcxqqisqfdeeuokphclytdfbtogmmrglfbcbtbhgrjmhyzthidybwqdywrryzunfmvuvjuvkdjcwyqwwoaotsmvicodnngxdwwxlkw";
        String s2 = "lkrzcpwilhhuukffdtbajghpqrjsypwzkuviqhbxtnzlubuzyveujbnljkttzwmjdrukrzeswttdzjfvqrlfzztguavagwcugmmhxjdqpoagxemkvhfnhgclsejzfsfttiwvjmfqufqoprdbegoqoucaxyylxmwfbmkujckwnnskxvkedowmwvabmdkeeclvjubydnihymgrdzodajprsfaodwqovmgmzrvtsshlbkbthbheumgsknwqbdqohxkkbxjsmicwlujfpilukllevoleztzxpplwcwsngpbhugofynxnbhfkngxjfmovhzsxftjthzytgpnejbnfqbeowjanppgxkqnxxdkpakqvpkbefthbiemgwwifskvysbmbhuoheqrgqpyaziuiwwhhduzorzqggiffxxhdihzxdncvrlvzitiewgwlbgylufpianpekzhwlecdhyrubcttfruxmzfukiirciagaqchvluyjsmzvpahtrtaisuzrqagutlasdgbxvqyqhhxozzyxouxfrvikrfaawneoyyornaxbcizbrhtytwrljwazyklydnsviwfcfgrkawirkhoyhrbpppifbdnknkqdkzmbcfrmqkbsziarbwadssrliujdgwrxjoduyancvsabuqrmgyppjnkxvuthzquaxsbxkdsavvrgyxsklfizfsycyrnhymmbjcxvhlcxqpeatipzxdzfayuqhvudbpwoowcvbwpjgfiigrskssigdnuuhhfxrrcdvtmnsverwpnjefnzirghlxnizusztvjawchiecdgtsawogxdxjaewgiyabjywhhnkfbdrucihhxopxmxrxyslcumfsrurnsybwcebxrcqlmthilndrfwzvvsqirsqmryluwavjjlvdstefpxylinmxgmvlzjoewwpracbkixoapezvdsjoamarnsgzufzdgxteypomrqqkazsxrbvkmviisvanumskxkpzrrvmletqanboionifpdszyleowjagagglnamwmopfindniokvkydsnayaezdmjfkbrfwuxbaoxicnoxfakwjmvfchqtnifuwtswwvvxircuzuhdobdqkzmczenfptpihpzhorjlqpqudijfqwzikwnhczkualaxiuebctizkqtcrxkpemzugpfpbaybsflzsvwnlnysnwsnlrdmtcrdgwurgioobcwjroiqlbsdcvemzaqtffiliwerqdispranlcvdyemmyyogrwbxqqghxoqytjnrzrdioejilthxpzkjbhjztasjvknjoklgwhtegujxvkcykabablssobxsyrkcjjobmkkfbyhwkztpnlgljqzydluiskkegngrzrkebbmufhglaqslieeosbywehrgtkfugaxzdsxnlcfudkdqqgjtvtxifbhjznjibqvvukkpsjttsexpqbpampciqoyxhiaciqdrwlliogcmfcbcxcqzhvvkmjbpmqgaxumaubjebxoanitykumqugkflemefahqvppjirmehsiyzgeguwoycquwyyyilmwmazcrldnivlnroblupdljmeaqzkwyuiufcvlazrthrhzbuucpmsummwhdjeqlzmsderjdekpuxovgucwnnmxcrkkaqnhlobdzdtpkpqtmhihrngnrygazklcpayqkfplqtcjfxejtpownqadaqwitghcykhxagevcsehinojnyfzqgaxulavxrkxunynztsnrdeciqkyuthqgtytatpvagbqnvbknailnesqqmughpeiqqkgfxuhxwqwgfrfgauiuqyyhupwccoosyvvvqbndxcptlncdpircgxyhxujqnaamxoyggyszbtbidggomlxzfyqlwlzrtjheckedypwcmyugvexfpnvagoebfmfrjcxbzaopvwggxhozpljvzdfmdotvczdbzavacgalwgdbjcjtzdxkdgtxzojyeixgaxluddgpqzzmilruccxacqqfgvlhdwmqwmmoacyamjlamcjkrfugbmzfdgdgmywyjexnnhuqixgyorzjzkyarvnkqcfnsohfuhlqdzjmsmdzrgcjqdevltghvtjfkcckeiesbldwjjarkjaocpwubzwqnuqikydqatbvokaxtbxakmrobpnzavjzctgjogmnbnjpvlmwlzrxutszuvtkrbxejyklaeqprhhcixtmonmvvhqhuqjffvmjjycgrgkdrlxkabymcuhesisrqmbumkjqxfeydpbbjflkteblsyscmibgiqovrxmvbejmjaztimulmoclmjwbepasijdlwuvirzbxrucawcipmpxrekogcuctkjabzuhwiefvereeyjbxproizfipckidjaybvymiwpeuiqcatokgdeedufezzbkwcqqocfqquecofkzjlshjhgkhavgltyuxdhkxddhyddgttzddofhbtmlsykfcoffxfhgfcuntegtwvxfkkitogwkpgidtmbckbddialbloyswuvtspzwqygllsnczknbtluooxquzbefgpbldjeowdxtvnyertifubiuyyuoqkqcpvszrutjmuywyxabwzfdvodkyzrbthoemktxedeuevzgwstdvfsskqusecqgymzzbohavgvtgrhxvvturrqxwtwgghbpnvftrqsnktgczshbdoabtptehehaoisrwzmbtvpzphzwhvxauswemkkgqexnedlzwaxhuhbybnwldcpkbxalccoymlhqqjyzhwrkukgvyuefvjargjkxnuerbywamzpbhottksicixcumvtypaogmtxjshajptmpicjmrpmytzqiihiecvufqoqthdmwflydwwcfqrnschgdrciweyfvxcpyebixanzixahhudrtrxtuhvfdyzwapcabpfunjmmvhufsdkncqensiibrfjijvsxjdqzclcaiyoebiatmnmiufbtjnhschoxboojjagijficguwpluyyqosprjahcqdibeckzbxoezbiglakdwrcnmjanilmudvqivxnifrrbgmpyjfwujupzalhrhptiplxvsuoybzlyoyqdjcpgxmapxftbojsqwnaedfefufvvaipnsghylbqdnomkhbrraikxnmvancqdoihitpksiqspaijzwuohbqtolxjysaiayoskijnkgmrppvyspslvvzeemcanvylpgjuxwtwzonjliuxtlyjjhogytdaeiozfamrqpsrirjrgruyfwlonybchhrejhktxewddffddqfesenfrjcujmvbtzgfigqmwhmbwtofsfuududneljnqagnlgzzwwdzfuxyegpdkbeflyidylgajptpnlyukptxhcpafqiphwgbuforjybejzgnnofztniulgdficdbotmnnjthzqxuxzmgoojklscosjtvktcwiuiifcqvqwpxwjcyliwkfstuxdmztwoaxcmchdewbkhjfonbrcstqnyhtpentvxcdotqqsshurypjzksguektjmtgbonbspsvhsxcrbdzeifogivhrybexhfcupronmirisrafdknbrnqkrgamfvebykmjeljzrsmuwdlvbojjrarzwalcyxgndcyhfiegglrmmfhxqpwiougyhqnajsfkatzdnwyldbghvzknujzjsdsluoyexhnswlmdyjiimbvdqgukxznrampsasojqhdpjsmkicinkojylnaxjumqwvdsmuvupxdpcfjdcebavjcpclkbbgejwnzmyqzbhgxnytmvhjepvkaugeofvziekkwhyjhonveoqotuedyhrskyjlcibwltuhssxvxwzrjvaekqkpaksgnkumffotelesbuwfxuyrrziktkuxzycsxyumafpqlnnwdbowjzzombnwnrcaxmpmywmvhnfcqdbgdzgbcxsjfuhnhgegctusleqwtojzvabyluilyyunzmslnbqgjrtwibnvyyzzfbjoqhfaokdkdlpsdyigglbvmmvsgikigvdabwupgpdxtykfrvzoxeefuxyptzecpvhbsnkbbelaytkwrstcwczqzbgjbobfaxsybvurphknedlepfuuzkpyzfodmkqdffbwyqnxgxnsthfgwbjibpfjnuoritrtgbkyjrcujgpuoweuobawgzllrrtcauxnyvvrqaacadjhdpfgvuyusfjdawnovfpcalutebuclttnssqwueylqlkmwnujudawnxtyzbtgonyroppwexmubgeegecbpsafywiniyfoxqyrcwogjuslorfzyboiifwrhggfodbuzqvzdxelquloyolmrushxzfiuzdojdjtogpruxpfceekoouzwktmsmfzdcqkyubgasdxatuhbrlnlptmjsafplmlfntrjovrqkugldzvvrelinxvazfkjygnnfchmajqqdynpdkjsmbjysajeqegofqhakrcahngsiswjoixkodyygmumyngnsjuvvbackdkzfbcjzwoeyxengdgxsgkrrpsrrhiigxanriytyjktjoouflfcxerivldvgbasowoawajqxnumrbhgiaspakmadtuvirwthbvppsgafteztgckcjttxzqfmxfcfumzcagyzexxcbhkyrkmwevqghfjiuopnpcqkjfpceuztzhxsvcdclflkabglwqdfgofrbqbjmunmhivczndkunxapbwwjxieiwsncvpclvhtwtqiguysqkugmiuollhoixwnsmupcplmrxlqprordkqurhtncllueskaynopaekidvjgfqepjffmfezklllxmkezjwnnloxlvdultvarifopnugajspzypnwsmomdcpouozmygrppbftzpxipkvklttffcxqqisqfdeeuokphclytdfbtogmmrglfbcbtbhgrjmhyzthidybwqdywrryzrnfmnuvjuvkdjcwyqwwoaotsmvicodndgxdwwxlkwtbflcnulyjfptodffqvlxjkhvwdoonxanrwjqnbtbvzpsrfrpcrdealcyhjfdqsckbrpyeduwnllelbvrshdeiasmebfhwfiofddqmvnewsapvfgdeqltoreinprmnhrfzvsjkqjgohzpgekjcnzskbwpxkkdsirshozmpnpvsbmccxebhxlilcubgfwmvislgtzovotufddbuynsmcsefqydbeelnhxpbsdiwyfrnyqzmoyzcewelkxtcohyamcauvvwclzxsgtqnhiuilbqidqmpqxtqskrxtsbixruwhadfpfpvmhphlewrblojkcpdbmqiitviohofbjxzdgfkbotxhzxtahhipvbctlbwypkhkcmkvqkerhbpkefhztyosrkknppcfqbohfuogwxecxpxttbaboidbhacrhevtrmukakzkuqlwtugxhzljwtbvluaaskjvnpngsicuznwrpbfzhhidraqwenxvcnbnooqpjyqnidypuokvuyqftbnmpvwsenpcvvmnlonxyooiicqzwasbtoasxsmsddczxvknupxtlwoolyjytzzkmfvlzggwosjahjevbaspveqxqyxuvpprgjifakmostvgqtrrikymrgrameejhvbatmgzuvdeljiipbvgwolhorfxsgramkfpyfvopuxckhvsrhwgdfaauhpmsyqfbsevgwdynhypxhekpfzxxslkbqgclczlxgpvfoxfthrhaqkhqegmxrzsbtmstvcabovuwzsgondounxyrtutjpocrnzwmoctucklqwiyvvnzucemwzwapnmqjmvezkrbeaznhjijfzqyzounzosgcitlyhviyjiedyzxpzbhkojasegsvewoimcoajhiincnlztekigtcudtdytyxnorzmyghxcpuvljtjghqoqfxirmsistcmsiazlohaflkfawegkfowlpowpogggdvsrgfkzjlgtxcslqvkdrcpvexvhnuohjdmuqoyvsbyysvbgmvmldqbmcxnutdbftxtiaiihxudsucgzuipmxpyezvhexadlyabrgtukalafiqeczlbihmpbxyerdzsrisulxdfxsnwtolvlynrotowbvjuckrmywqomlxiwvltgvwkdcovvkzebtumcdpwdbwrnflbkqktnuzjpchwhpxbknfyvqljjqwpfzldyhzlpcuccyllvdaezcrznsbvomriadouenndwyxvclrcjpkoivxmjrwkqrlrijexvxhnpbmwkpvqpbkcqxydrwmpdzykefjjssbtotkvoitduesbfeiqfjijwqofledklqmkgssgieplevysqrluzqpavwliosrouzczdyhxhtjtzoudqptlqectrsphiyevuesqictudybuplshepjkjbtujcpxvobqzzxpgnwwvpenkllotcnlakylegkokkygqojivxhnlrpkwmuhcscoyykexlikaouocjgosenadwktjistlbkbjecepgknoljvvdzruwextgaaruunbiihinvsc";
        boolean b = checkInclusion(s1, s2);
        assertTrue(b);
    }

    /**
     * 输入: s1 = "abc", s2 = "ccccbbbbaaaa"
     * 输出: false
     */
    @Test
    public void test7() {
        String s1 = "abc";
        String s2 = "ccccbbbbaaaa";
        boolean b = checkInclusion(s1, s2);
        assertFalse(b);
    }
}

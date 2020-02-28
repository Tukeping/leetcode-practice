package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=49 lang=java
 *
 * [49] 字母异位词分组
 *
 * https://leetcode-cn.com/problems/group-anagrams/description/
 *
 * algorithms
 * Medium (59.69%)
 * Likes:    264
 * Dislikes: 0
 * Total Accepted:    49.4K
 * Total Submissions: 81.3K
 * Testcase Example:  '["eat","tea","tan","ate","nat","bat"]'
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ⁠ ["ate","eat","tea"],
 * ⁠ ["nat","tan"],
 * ⁠ ["bat"]
 * ]
 *
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNull;

/**
 * hash-table | string
 *
 * amazon | bloomberg | facebook | uber | yelp
 *
 * frequency 4
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        // corner case
        if (strs == null) return null;
        if (strs.length == 0) return Collections.emptyList();
        Map<String, List<String>> map = toMap(strs);
        return new ArrayList<>(map.values());
    }

    private Map<String, List<String>> toMap(String[] strs) {
        Map<String, List<String>> map = new HashMap<>(strs.length);
        for (String originStr : strs) {
            String sortedStr = toSortString(originStr.toCharArray());
            List<String> L;
            if (map.containsKey(sortedStr)) {
                L = map.get(sortedStr);
                L.add(originStr);
            } else {
                L = new ArrayList<>();
                L.add(originStr);
                map.put(sortedStr, L);
            }
        }
        return map;
    }

    private String toSortString(char[] chars) {
        Arrays.sort(chars);
        return new String(chars);
    }

    private void permute(char[] chars, LinkedList<Character> track, List<Character[]> res) {
        if (track.size() == chars.length) {
            res.add(track.toArray(new Character[0]));
            return;
        }

        for (char c : chars) {
            if (track.contains(c))
                continue;

            track.add(c);
            permute(chars, track, res);
            track.removeLast();
        }
    }

    private char[] swap(char[] chars, int a, int b) {
        char tmp = chars[a];
        chars[a] = chars[b];
        chars[b] = tmp;
        return chars;
    }

    @Test
    public void test1() {
        List<List<String>> actual = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        String[][] expectDim = {
                {"ate", "eat", "tea"},
                {"nat", "tan"},
                {"bat"}
        };
        List<List<String>> expect = ListHelper.asDoubleList(expectDim);
        ListHelper.checkDoubleListInAnyOrder(actual, expect);
    }

    @Test
    public void test2() {
        List<List<String>> res = groupAnagrams(null);
        assertNull(res);
    }

    @Test
    public void test3() {
        List<List<String>> actual = groupAnagrams(new String[]{"", ""});
        String[][] expectDim = {
                {"", ""}
        };
        List<List<String>> expect = ListHelper.asDoubleList(expectDim);
        ListHelper.checkDoubleListInAnyOrder(actual, expect);
    }

    @Test
    public void test4() {
        List<List<String>> actual = groupAnagrams(new String[]{"", "b", ""});
        String[][] expectDim = {
                {"", ""},
                {"b"}
        };
        List<List<String>> expect = ListHelper.asDoubleList(expectDim);
        ListHelper.checkDoubleListInAnyOrder(actual, expect);
    }

    @Test
    public void test5() {
        List<List<String>> actual = groupAnagrams(new String[]{"tea", "", "eat", "", "tea", ""});
        String[][] expectDim = {
                {"eat", "tea", "tea"},
                {"", "", ""}
        };
        List<List<String>> expect = ListHelper.asDoubleList(expectDim);
        ListHelper.checkDoubleListInAnyOrder(actual, expect);
    }

    @Test
    public void test6() {
        List<List<String>> actual = groupAnagrams(new String[]{"paw", "dad", "bog", "day", "day", "mig", "len", "rat"});
        String[][] expectDim = {
                {"rat"},
                {"mig"},
                {"paw"},
                {"dad"},
                {"len"},
                {"bog"},
                {"day", "day"}
        };
        List<List<String>> expect = ListHelper.asDoubleList(expectDim);
        ListHelper.checkDoubleListInAnyOrder(actual, expect);
    }

    @Test
    public void test7() {
        List<List<String>> actual = groupAnagrams(new String[]{"mod", "mop", "pip", "tug", "hop", "dog", "met", "zoe", "axe", "mug", "fdr", "for", "fro", "fdr", "pap", "ray", "lop", "nth", "old", "eva", "ell", "mci", "wee", "ind", "but", "all", "her", "lew", "ken", "awl", "law", "rim", "zit", "did", "yam", "not", "ref", "lao", "gab", "sax", "cup", "new", "job", "new", "del", "gap", "win", "pot", "lam", "mgm", "yup", "hon", "khz", "sop", "has", "era", "ark"});
        String[][] expectDim = {
                {"gap"},
                {"has"},
                {"dog"},
                {"ark"},
                {"ind"},
                {"ray"},
                {"era"},
                {"axe"},
                {"lam"},
                {"zoe"},
                {"hop"},
                {"old"},
                {"met"},
                {"mug"},
                {"nth"},
                {"wee"},
                {"all"},
                {"but"},
                {"ken"},
                {"her"},
                {"lew"},
                {"mod"},
                {"yam"},
                {"job"},
                {"win"},
                {"del"},
                {"sop"},
                {"hon"},
                {"yup"},
                {"mgm"},
                {"pot"},
                {"mci"},
                {"eva"},
                {"zit"},
                {"did"},
                {"pap"},
                {"rim"},
                {"gab"},
                {"lop"},
                {"ref"},
                {"sax"},
                {"ell"},
                {"khz"},
                {"not"},
                {"tug"},
                {"lao"},
                {"mop"},
                {"cup"},
                {"pip"},
                {"fdr", "fdr"},
                {"awl", "law"},
                {"for", "fro"},
                {"new", "new"}
        };
        List<List<String>> expect = ListHelper.asDoubleList(expectDim);
        ListHelper.checkDoubleListInAnyOrder(actual, expect);
    }

    @Test
    public void test8() {
        List<List<String>> actual = groupAnagrams(new String[]{"compilations", "bewailed", "horology", "lactated", "blindsided", "swoop", "foretasted", "ware", "abuts", "stepchild", "arriving", "magnet", "vacating", "relegates", "scale", "melodically", "proprietresses", "parties", "ambiguities", "bootblacks", "shipbuilders", "umping", "belittling", "lefty", "foremost", "bifocals", "moorish", "temblors", "edited", "hint", "serenest", "rendezvousing", "schoolmate", "fertilizers", "daiquiri", "starr", "federate", "rectal", "case", "kielbasas", "monogamous", "inflectional", "zapata", "permitted", "concessions", "easters", "communique", "angelica", "shepherdess", "jaundiced", "breaks", "raspy", "harpooned", "innocence", "craters", "cajun", "pueblos", "housetop", "traits", "bluejacket", "pete", "snots", "wagging", "tangling", "cheesecakes", "constructing", "balanchine", "paralyzed", "aftereffects", "dotingly", "definitions", "renovations", "surfboards", "lifework", "knacking", "apprises", "minimalism", "skyrocketed", "artworks", "instrumentals", "eardrums", "hunching", "codification", "vainglory", "clarendon", "peters", "weeknight", "statistics", "ay", "aureomycin", "lorrie", "compassed", "speccing", "galen", "concerto", "rocky", "derision", "exonerate", "sultrier", "mastoids", "repackage", "cyclical", "gowns", "regionalism", "supplementary", "bierce", "darby", "memorize", "songster", "biplane", "calibrates", "decriminalizes", "shack", "idleness", "confessions", "snippy", "barometer", "earthing", "sequence", "hastiness", "emitted", "superintends", "stockades", "busywork", "dvina", "aggravated", "furbelow", "hashish", "overextended", "foreordain", "lie", "insurance", "recollected", "interpreted", "congregate", "ranks", "juts", "dampen", "gaits", "eroticism", "neighborhoods", "perihelion", "simulations", "fumigating", "balkiest", "semite", "epicure", "heavier", "masterpiece", "bettering", "lizzie", "wail", "batsmen", "unbolt", "cudgeling", "bungalow", "behalves", "refurnishes", "pram", "spoonerisms", "cornered", "rises", "encroachments", "gabon", "cultivation", "parsed", "takeovers", "stampeded", "persia", "devotional", "doorbells", "psalms", "cains", "copulated", "archetypal", "cursores", "inbred", "paradigmatic", "thesauri", "rose", "stopcocks", "weakness", "ballsier", "jagiellon", "torches", "hover", "conservationists", "brightening", "dotted", "rodgers", "mandalay", "overjoying", "supervision", "gonads", "portage", "crap", "capers", "posy", "collateral", "funny", "garvey", "ravenously", "arias", "kirghiz", "elton", "gambolled", "highboy", "kneecaps", "southey", "etymology", "overeager", "numbers", "ebullience", "unseemly", "airbrushes", "excruciating", "gemstones", "juiciest", "muftis", "shadowing", "organically", "plume", "guppy", "obscurely", "clinker", "confederacies", "unhurried", "monastic", "witty", "breastbones", "ijsselmeer", "dublin", "linnaeus", "dervish", "bluefish", "selectric", "syllable", "pogroms", "pacesetters", "anastasia", "pandora", "foci", "bipartisan", "loomed", "emits", "gracious", "warfare", "uncouples", "augusts", "portray", "refinery", "resonances", "expediters", "deputations", "indubitably", "richly", "motivational", "gringo", "hubris", "mislay", "scad", "lambastes", "reemerged", "wart", "zirconium", "linus", "moussorgsky", "swopped", "sufferer", "sputtered", "tamed", "merrimack", "conglomerate", "blaspheme", "overcompensate", "rheas", "pares", "ranted", "prisoning", "rumor", "gabbles", "lummox", "lactated", "unzipping", "tirelessly", "backdate", "puzzling", "interject", "rejections", "bust", "centered", "oxymoron", "tangibles", "sejong", "not", "tameness", "consumings", "prostrated", "rowdyism", "ardent", "macabre", "rustics", "dodoes", "warheads", "wraths", "bournemouth", "staffers", "retold", "stiflings", "petrifaction", "larkspurs", "crunching", "clanks", "briefest", "clinches", "attaching", "extinguished", "ryder", "shiny", "antiqued", "gags", "assessments", "simulated", "dialed", "confesses", "livelongs", "dimensions", "lodgings", "cormorants", "canaries", "spineless", "widening", "chappaquiddick", "blurry", "lassa", "vilyui", "desertions", "trinket", "teamed", "bidets", "mods", "lessors", "impressiveness", "subjugated", "rumpuses", "swamies", "annotations", "batiks", "ratliff", "waxwork", "grander", "junta", "chutney", "exalted", "yawl", "joke", "vocational", "diabetic", "bullying", "edit", "losing", "banns", "doleful", "precision", "excreting", "foals", "smarten", "soliciting", "disturbance", "soggily", "gabrielle", "margret", "faded", "pane", "jerusalem", "bedpan", "overtaxed", "brigs", "honors", "repackage", "croissants", "kirov", "crummier", "limeades", "grandson", "criers", "bring", "jaundicing", "omnibusses", "gawking", "tonsillectomies", "deodorizer", "nosedove", "commence", "faulkner", "adultery", "shakedown", "wigwag", "wiper", "compatible", "ultra", "adamant", "distillation", "gestates", "semi", "inmate", "onlookers", "grudgingly", "recipe", "chaise", "dialectal", "aphids", "flimsier", "orgasm", "sobs", "swellheaded", "utilize", "karenina", "irreparably", "preteen", "mumble", "gingersnaps", "alumnus", "chummiest", "snobbish", "crawlspaces", "inappropriate", "ought", "continence", "hydrogenate", "eskimo", "desolated", "oceanic", "evasive", "sake", "laziest", "tramps", "joyridden", "acclimatized", "riffraff", "thanklessly", "harmonizing", "guinevere", "demanded", "capabler", "syphilitics", "brainteaser", "creamers", "upholds", "stiflings", "walt", "luau", "deafen", "concretely", "unhand", "animations", "map", "limbos", "tranquil", "windbreakers", "limoges", "varying", "declensions", "signs", "green", "snowbelt", "homosexual", "hopping", "residue", "ransacked", "emeritus", "pathologist", "brazenly", "forbiddingly", "alfredo", "glummest", "deciphered", "delusive", "repentant", "complainants", "beets", "syntactics", "vicissitude", "incompetents", "concur", "canaan", "rowdies", "streamer", "martinets", "shapeliness", "videodiscs", "restfulness", "rhea", "consumed", "pooching", "disenfranchisement", "impoverishes", "behalf", "unsuccessfully", "complicity", "ulcerating", "derisive", "jephthah", "clearing", "reputation", "kansan", "sledgehammer", "benchmarks", "escutcheon", "portfolios", "mandolins", "marketable", "megalomaniacs", "kinking", "bombarding", "wimple", "perishes", "rukeyser", "squatter", "coddle", "traditionalists", "sifts", "agglomerations", "seasonings", "brightness", "spices", "claimant", "sofas", "ambulatories", "bothered", "businessmen", "orly", "kinetic", "contracted", "grenadiers", "flooding", "dissolved", "corroboration", "mussed", "squareness", "alabamans", "dandelions", "labyrinthine", "pot", "waxwing", "residential", "pizza", "overjoying", "whelps", "overlaying", "elanor", "tented", "masterminded", "balsamed", "powerhouses", "tramps", "eisenstein", "voile", "repellents", "beaus", "coordinated", "wreckers", "eternities", "untwists", "estrangements", "vitreous", "embodied"});
        String[][] expectDim = {
                {"embodied"},
                {"wreckers"},
                {"powerhouses"},
                {"balsamed"},
                {"tented"},
                {"overlaying"},
                {"untwists"},
                {"whelps"},
                {"labyrinthine"},
                {"dandelions"},
                {"squareness"},
                {"mussed"},
                {"flooding"},
                {"contracted"},
                {"kinetic"},
                {"orly"},
                {"businessmen"},
                {"bothered"},
                {"ambulatories"},
                {"spices"},
                {"brightness"},
                {"agglomerations"},
                {"traditionalists"},
                {"coddle"},
                {"wimple"},
                {"bombarding"},
                {"megalomaniacs"},
                {"marketable"},
                {"benchmarks"},
                {"kansan"},
                {"reputation"},
                {"clearing"},
                {"derisive"},
                {"complicity"},
                {"pooching"},
                {"restfulness"},
                {"shapeliness"},
                {"streamer"},
                {"rowdies"},
                {"canaan"},
                {"martinets"},
                {"concur"},
                {"incompetents"},
                {"vicissitude"},
                {"beets"},
                {"repentant"},
                {"deciphered"},
                {"alfredo"},
                {"impoverishes"},
                {"forbiddingly"},
                {"brazenly"},
                {"residue"},
                {"hopping"},
                {"emeritus"},
                {"snowbelt"},
                {"green"},
                {"declensions"},
                {"varying"},
                {"limoges"},
                {"masterminded"},
                {"limbos"},
                {"map"},
                {"animations"},
                {"concretely"},
                {"luau"},
                {"creamers"},
                {"brainteaser"},
                {"syphilitics"},
                {"capabler"},
                {"thanklessly"},
                {"joyridden"},
                {"tramps", "tramps"},
                {"sake"},
                {"evasive"},
                {"desolated"},
                {"eskimo"},
                {"hydrogenate"},
                {"crawlspaces"},
                {"alumnus"},
                {"utilize"},
                {"dissolved"},
                {"swellheaded"},
                {"orgasm"},
                {"rhea"},
                {"homosexual"},
                {"flimsier"},
                {"aphids"},
                {"recipe"},
                {"portage"},
                {"foreordain"},
                {"continence"},
                {"gonads"},
                {"rodgers"},
                {"hubris"},
                {"conservationists"},
                {"hover"},
                {"kinking"},
                {"pueblos"},
                {"weakness"},
                {"stopcocks"},
                {"rose"},
                {"irreparably"},
                {"weeknight"},
                {"instrumentals"},
                {"persia"},
                {"harmonizing"},
                {"doleful"},
                {"inflectional"},
                {"sobs"},
                {"takeovers"},
                {"permitted"},
                {"parsed"},
                {"guinevere"},
                {"encroachments"},
                {"behalves"},
                {"thesauri"},
                {"case"},
                {"rises"},
                {"cornered"},
                {"eisenstein"},
                {"pram"},
                {"bungalow"},
                {"breastbones"},
                {"margret"},
                {"waxwing"},
                {"cudgeling"},
                {"wail"},
                {"claimant"},
                {"lizzie"},
                {"coordinated"},
                {"overjoying", "overjoying"},
                {"heavier"},
                {"neighborhoods"},
                {"gaits"},
                {"interpreted"},
                {"pizza"},
                {"insurance"},
                {"archetypal"},
                {"batiks"},
                {"congregate"},
                {"glummest"},
                {"spoonerisms"},
                {"speccing"},
                {"plume"},
                {"dvina"},
                {"dimensions"},
                {"commence"},
                {"earthing"},
                {"balkiest"},
                {"dampen"},
                {"lie"},
                {"snippy"},
                {"idleness"},
                {"numbers"},
                {"larkspurs"},
                {"overtaxed"},
                {"crummier"},
                {"decriminalizes"},
                {"videodiscs"},
                {"delusive"},
                {"tranquil"},
                {"exonerate"},
                {"ravenously"},
                {"pane"},
                {"calibrates"},
                {"biplane"},
                {"capers"},
                {"crap"},
                {"chummiest"},
                {"songster"},
                {"supplementary"},
                {"cyclical"},
                {"repackage", "repackage"},
                {"unbolt"},
                {"bournemouth"},
                {"syntactics"},
                {"epicure"},
                {"minimalism"},
                {"emitted"},
                {"confessions"},
                {"edit"},
                {"unsuccessfully"},
                {"rectal"},
                {"portfolios"},
                {"deodorizer"},
                {"constructing"},
                {"rendezvousing"},
                {"ulcerating"},
                {"derision"},
                {"expediters"},
                {"gringo"},
                {"federate"},
                {"hint"},
                {"seasonings"},
                {"croissants"},
                {"arriving"},
                {"concessions"},
                {"knacking"},
                {"blurry"},
                {"bipartisan"},
                {"upholds"},
                {"superintends"},
                {"statistics"},
                {"perishes"},
                {"sequence"},
                {"foremost"},
                {"sofas"},
                {"cormorants"},
                {"rheas"},
                {"belittling"},
                {"laziest"},
                {"wagging"},
                {"umping"},
                {"gags"},
                {"easters"},
                {"onlookers"},
                {"ought"},
                {"monogamous"},
                {"grudgingly"},
                {"moussorgsky"},
                {"bust"},
                {"regionalism"},
                {"tangibles"},
                {"proprietresses"},
                {"cursores"},
                {"traits"},
                {"acclimatized"},
                {"pete"},
                {"lefty"},
                {"disenfranchisement"},
                {"edited"},
                {"bootblacks"},
                {"consumed"},
                {"scad"},
                {"ware"},
                {"simulations"},
                {"gabon"},
                {"airbrushes"},
                {"dialectal"},
                {"refurnishes"},
                {"eternities"},
                {"doorbells"},
                {"horology"},
                {"walt"},
                {"furbelow"},
                {"mastoids"},
                {"widening"},
                {"copulated"},
                {"torches"},
                {"sufferer"},
                {"bifocals"},
                {"wart"},
                {"stepchild"},
                {"ebullience"},
                {"darby"},
                {"memorize"},
                {"vacating"},
                {"temblors"},
                {"ijsselmeer"},
                {"vitreous"},
                {"jaundiced"},
                {"magnet"},
                {"grander"},
                {"inbred"},
                {"shadowing"},
                {"corroboration"},
                {"preteen"},
                {"schoolmate"},
                {"snots"},
                {"escutcheon"},
                {"rustics"},
                {"compilations"},
                {"alabamans"},
                {"waxwork"},
                {"barometer"},
                {"losing"},
                {"lactated", "lactated"},
                {"sifts"},
                {"bettering"},
                {"sultrier"},
                {"ardent", "ranted"},
                {"windbreakers"},
                {"gowns"},
                {"joke"},
                {"paradigmatic"},
                {"hunching"},
                {"exalted"},
                {"vainglory"},
                {"melodically"},
                {"kneecaps"},
                {"busywork"},
                {"oceanic"},
                {"communique"},
                {"lambastes"},
                {"dotingly"},
                {"sledgehammer"},
                {"angelica"},
                {"shepherdess"},
                {"surfboards"},
                {"breaks"},
                {"jephthah"},
                {"funny"},
                {"beaus"},
                {"kielbasas"},
                {"harpooned"},
                {"gingersnaps"},
                {"craters"},
                {"pogroms"},
                {"brigs"},
                {"eroticism"},
                {"spineless"},
                {"parties"},
                {"cajun"},
                {"compassed"},
                {"unzipping"},
                {"karenina"},
                {"nosedove"},
                {"confederacies"},
                {"posy"},
                {"clarendon"},
                {"complainants"},
                {"chaise"},
                {"elton"},
                {"bedpan"},
                {"ballsier"},
                {"richly"},
                {"omnibusses"},
                {"supervision"},
                {"swoop"},
                {"apprises"},
                {"batsmen"},
                {"not"},
                {"starr"},
                {"balanchine"},
                {"bluejacket"},
                {"peters"},
                {"tangling"},
                {"pot"},
                {"relegates"},
                {"hastiness"},
                {"paralyzed"},
                {"estrangements"},
                {"masterpiece"},
                {"faulkner"},
                {"lorrie"},
                {"aggravated"},
                {"mods"},
                {"artworks"},
                {"juts"},
                {"excreting"},
                {"daiquiri"},
                {"aftereffects"},
                {"fumigating"},
                {"ranks"},
                {"eardrums"},
                {"mislay"},
                {"aureomycin"},
                {"renovations"},
                {"impressiveness"},
                {"recollected"},
                {"stockades"},
                {"zapata"},
                {"portray"},
                {"lifework"},
                {"abuts"},
                {"reemerged"},
                {"semite"},
                {"psalms"},
                {"warheads"},
                {"fertilizers"},
                {"staffers"},
                {"skyrocketed"},
                {"stampeded"},
                {"grenadiers"},
                {"interject"},
                {"pathologist"},
                {"concerto"},
                {"hashish"},
                {"overcompensate"},
                {"sputtered"},
                {"smarten"},
                {"collateral"},
                {"garvey"},
                {"uncouples"},
                {"extinguished"},
                {"arias"},
                {"serenest"},
                {"kirghiz"},
                {"cheesecakes"},
                {"highboy"},
                {"repellents"},
                {"galen"},
                {"southey"},
                {"etymology"},
                {"gemstones"},
                {"unseemly"},
                {"inmate"},
                {"bierce"},
                {"excruciating"},
                {"shipbuilders"},
                {"semi"},
                {"juiciest"},
                {"muftis"},
                {"bidets"},
                {"organically"},
                {"ambiguities"},
                {"rumpuses"},
                {"guppy"},
                {"mumble"},
                {"obscurely"},
                {"definitions"},
                {"clinker"},
                {"unhurried"},
                {"voile"},
                {"monastic"},
                {"mandalay"},
                {"witty"},
                {"deafen"},
                {"honors"},
                {"wiper"},
                {"rocky"},
                {"adamant"},
                {"elanor"},
                {"subjugated"},
                {"dervish"},
                {"devotional"},
                {"bluefish"},
                {"selectric"},
                {"syllable"},
                {"dotted"},
                {"shakedown"},
                {"pacesetters"},
                {"lassa"},
                {"anastasia"},
                {"rejections"},
                {"pandora"},
                {"tamed"},
                {"desertions"},
                {"foci"},
                {"jaundicing"},
                {"emits"},
                {"dublin"},
                {"gracious"},
                {"warfare"},
                {"briefest"},
                {"augusts"},
                {"raspy"},
                {"refinery"},
                {"overextended"},
                {"ryder"},
                {"deputations"},
                {"clinches"},
                {"codification"},
                {"indubitably"},
                {"motivational"},
                {"resonances"},
                {"zirconium"},
                {"linus"},
                {"dialed"},
                {"merrimack"},
                {"conglomerate"},
                {"blaspheme"},
                {"housetop"},
                {"prisoning"},
                {"ransacked"},
                {"swopped"},
                {"rumor"},
                {"shack"},
                {"ay"},
                {"gabbles"},
                {"lummox"},
                {"consumings"},
                {"petrifaction"},
                {"tirelessly"},
                {"backdate"},
                {"inappropriate"},
                {"puzzling"},
                {"centered"},
                {"demanded"},
                {"oxymoron"},
                {"sejong"},
                {"tameness"},
                {"prostrated"},
                {"brightening"},
                {"rowdyism"},
                {"macabre"},
                {"wraths"},
                {"behalf"},
                {"cultivation"},
                {"retold"},
                {"stiflings", "stiflings"},
                {"clanks"},
                {"antiqued"},
                {"residential"},
                {"assessments"},
                {"simulated"},
                {"confesses"},
                {"livelongs"},
                {"lodgings"},
                {"blindsided"},
                {"canaries"},
                {"foretasted"},
                {"banns"},
                {"chappaquiddick"},
                {"vilyui"},
                {"tonsillectomies"},
                {"trinket"},
                {"teamed"},
                {"innocence"},
                {"lessors"},
                {"overeager"},
                {"swamies"},
                {"squatter"},
                {"shiny"},
                {"annotations"},
                {"junta"},
                {"chutney"},
                {"cains"},
                {"bring"},
                {"rukeyser"},
                {"yawl"},
                {"diabetic"},
                {"mandolins"},
                {"perihelion"},
                {"ratliff"},
                {"bullying"},
                {"riffraff"},
                {"precision"},
                {"moorish"},
                {"foals"},
                {"signs"},
                {"attaching"},
                {"adultery"},
                {"soliciting"},
                {"linnaeus"},
                {"disturbance"},
                {"jagiellon"},
                {"loomed"},
                {"dodoes"},
                {"soggily"},
                {"unhand"},
                {"vocational"},
                {"gabrielle"},
                {"faded"},
                {"jerusalem"},
                {"kirov"},
                {"limeades"},
                {"bewailed"},
                {"grandson"},
                {"snobbish"},
                {"crunching"},
                {"distillation"},
                {"scale"},
                {"criers"},
                {"gawking"},
                {"wigwag"},
                {"pares"},
                {"compatible"},
                {"gambolled"},
                {"ultra"},
                {"gestates"}
        };
        List<List<String>> expect = ListHelper.asDoubleList(expectDim);
        expect.sort((o1, o2) -> o2.size() - o1.size());
        ListHelper.checkDoubleListInAnyOrder(actual, expect);
    }
}

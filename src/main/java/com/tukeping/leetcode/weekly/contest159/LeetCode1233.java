package com.tukeping.leetcode.weekly.contest159;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/14
 **/
public class LeetCode1233 {

    public List<String> removeSubfolders(String[] folder) {
        int len = folder.length;

        List<String> ans = new ArrayList<>();

        Map<String, Boolean> folderMap = new HashMap<>();
        for (int i = 0; i < len; i++)
            folderMap.put(folder[i], Boolean.TRUE);

        for (int i = 0; i < len; i++) {
            if (!hasParentFolder(folder[i], folderMap)) {
                ans.add(folder[i]);
            }
        }

        return ans;
    }

    private boolean hasParentFolder(String folder, Map<String, Boolean> folderMap) {
        if (folder == null || folder.isEmpty()) {
            return false;
        }

        int idx = folder.lastIndexOf("/");
        if (idx == 0 || idx == -1) {
            return false;
        }

        String newFolder = folder.substring(0, idx);
        if (folderMap.containsKey(newFolder)) {
            return true;
        } else {
            return hasParentFolder(newFolder, folderMap);
        }
    }

    /**
     * 输入：folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
     * 输出：["/a","/c/d","/c/f"]
     * 解释："/a/b/" 是 "/a" 的子文件夹，而 "/c/d/e" 是 "/c/d" 的子文件夹。
     */
    @Test
    public void test1() {
        List<String> ans = removeSubfolders(new String[]{"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"});
        assertThat(ans, containsInAnyOrder("/a", "/c/d", "/c/f"));
    }

    /**
     * 输入：folder = ["/a","/a/b/c","/a/b/d"]
     * 输出：["/a"]
     * 解释：文件夹 "/a/b/c" 和 "/a/b/d/" 都会被删除，因为它们都是 "/a" 的子文件夹。
     */
    @Test
    public void test2() {
        List<String> ans = removeSubfolders(new String[]{"/a", "/a/b/c", "/a/b/d"});
        assertThat(ans, containsInAnyOrder("/a"));
    }

    /**
     * 输入：folder = ["/a/b/c","/a/b/d","/a/b/ca"]
     * 输出：["/a/b/c","/a/b/ca","/a/b/d"]
     */
    @Test
    public void test3() {
        List<String> ans = removeSubfolders(new String[]{"/a/b/c", "/a/b/d", "/a/b/ca"});
        assertThat(ans, containsInAnyOrder("/a/b/c", "/a/b/ca", "/a/b/d"));
    }
}

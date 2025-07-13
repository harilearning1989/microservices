package com.web.fake.jira;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;

public class JiraAttachmentUploader {
    public static void main(String[] args) throws Exception {
        //uploadFileJira();
        uploadFileToJira();
    }

    private static void uploadFileToJira() throws IOException {
        // Update these variables
        String jiraBase = "https://your-domain.atlassian.net";
        String issueKey = "PROJECT-123";
        String email = "your-email@example.com";
        String apiToken = "your-api-token";
        String filePath = "C:\\Users\\yourname\\file.txt";

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File not found: " + filePath);
            return;
        }

        String url = String.format("%s/rest/api/3/issue/%s/attachments", jiraBase, issueKey);
        HttpPost post = new HttpPost(url);

        // Set authentication and required headers
        String auth = email + ":" + apiToken;
        String encodedAuth = Base64.encodeBase64String(auth.getBytes("UTF-8"));
        post.setHeader("Authorization", "Basic " + encodedAuth);
        post.setHeader("X-Atlassian-Token", "no-check");

        // Build multipart entity with the file
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName());
        post.setEntity(builder.build());

        // Send request
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpResponse response = client.execute(post);
            int code = response.getStatusLine().getStatusCode();
            System.out.println("Response code: " + code);
            if (code >= 200 && code < 300) {
                System.out.println("File uploaded successfully!");
            } else {
                System.err.println("Upload failed. HTTP " + code);
            }
        }
    }

    private static void uploadFileJira() throws IOException {
        // 1. Update these variables
        String jiraBase = "https://synaptics-sandbox-766.atlassian.net";
        String issueKey = "XXTT2-1";
        String email = "vmullamu@synaptics.com";
        String apiToken = "ATATT3xFfGF0b8iOYTLljzyTxLBpyC9KH1nqyWVUbZoztiVas-mCj4osp6sFmZc6xvJhTzH8A6D9nAj0ZoQtu_6jaOFSiGflgUjsFaCBV8PCwNFE_3JvehfWNeirhVnc9yDHauo8mz7_eE4nMkQkgNVHTOzcXPTVdojycImoJgV5VwsiV6JW_fo=C83B1F1A";

        String filePath = "C:\\Users\\vmullamu\\test.txt";

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("❗ File not found: " + filePath);
            return;
        }

        //String filePath = "C:\\Users\\vmullamu\\Documents\\Java\\JiraUpload\\test.txt";
        //String filePath = "C:/Users/vmullamu/Documents/Java/JiraUpload/test.txt";
        //String filePath = System.getProperty("user.home");
        //Path filePath = Path.get(userHome, "Documents", "test.txt");
        //String userHome = System.getProperty("user.home");
        // Path path = Paths.get(userHome, "Documents", "test.txt");
        //
        // System.out.println("🔍 Path built: " + path.toAbsolutePath());
        //
        // // 2. Convert Path to File
        // File file = path.toFile();
        //
        // // 3. Debug information
        // System.out.println("Exists?   " + file.exists());
        // System.out.println("Is file?  " + file.isFile());
        // System.out.println("Readable? " + file.canRead());
        //
        // // 4. Early exit if file isn't available
        // if (!file.exists() || !file.canRead()) {
        //     System.err.println("❌ File not found or unreadable: " + path);
        //     return;
        // }
        //
        String url = String.format("%s/rest/api/3/issue/%s/attachments", jiraBase, issueKey);
        HttpPost post = new HttpPost(url);

        // 2. Seting basic auth header
        String auth = email + ":" + apiToken;
        String encodedAuth = Base64.encodeBase64String(auth.getBytes("UTF-8"));
        post.setHeader("Authorization", "Basic " + encodedAuth);
        post.setHeader("X-Atlassian-Token", "no-check");

        // 3. Building multipart entity with one file
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", file, ContentType.DEFAULT_BINARY, file.getName());
        post.setEntity(builder.build());

        // 4. Sending request
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpResponse response = client.execute(post);
            int code = response.getStatusLine().getStatusCode();
            System.out.println("Response code: " + code);
            if (code >= 200 && code < 300) {
                System.out.println("✅ File uploaded successfully!");
            } else {
                System.err.println("❌ Upload failed. HTTP " + code);
            }
        }
    }


}
 
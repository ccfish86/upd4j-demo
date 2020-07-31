package net.ccfish.upd4j.bootstrap;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.update4j.Configuration;
import org.update4j.service.Delegate;

public class BootstrapDelegate implements Delegate {

    @Override
    public void main(List<String> args) throws Throwable {
        System.out.println("BootApplication#main");
        
        TimerTask task = new TimerTask() {
            
            @Override
            public void run() {
                try {
                    start();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 5000, 60000);
    }

    @Override
    public long version() {
        return 2;
    }

    public void start() throws Exception {
        URL configUrl = new URL("http://172.16.251.22:8080/business/config.xml?" + System.currentTimeMillis());
        Configuration config = null;
        try (Reader in = new InputStreamReader(configUrl.openStream(), StandardCharsets.UTF_8)) {
            config = Configuration.read(in);
        } catch (IOException e) {
            System.err.println("Could not load remote config, falling back to local.");
            try (Reader in = Files.newBufferedReader(Paths.get("business/config.xml"))) {
                config = Configuration.read(in);
            }
        }

        StartupProgram startup = new StartupProgram(config);

        startup.launch();
    }

}

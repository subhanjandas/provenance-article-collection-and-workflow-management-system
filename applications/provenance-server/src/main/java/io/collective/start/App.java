package io.collective.start;

import io.collective.workflow.NoopTask;
import io.collective.workflow.NoopWorkFinder;
import io.collective.workflow.WorkScheduler;
import io.collective.workflow.Worker;

import java.util.List;

public class App {
    private final int port;
    private WorkScheduler<NoopTask> scheduler;

    public App(int port) {
        this.port = port;
    }

    public void start() {
        Worker<NoopTask> worker = new Worker<NoopTask>() {
            @Override
            public String getName() {
                return "worker";
            }

            @Override
            public void execute(NoopTask task) {
                // Worker execution logic
            }
        };

        scheduler = new WorkScheduler<>(new NoopWorkFinder(), List.of(worker), 300);
        scheduler.start();
    }

    public void stop() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }

    public static void main(String[] args) {
        int port = 8888; // Default port
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number, using default port 8888.");
            }
        }

        App app = new App(port);
        try {
            app.start();
            System.out.println("Server started on port " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add a shutdown hook to gracefully shut down the application
        Runtime.getRuntime().addShutdownHook(new Thread(app::stop));
    }
}

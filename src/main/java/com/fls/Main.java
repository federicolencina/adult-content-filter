// #=========================================================================#
//  #               This file is part of Adult Content Filter               #
//  #        https://github.com/federicolencina/adult-content-filter        #
//  #                                                                       #
//  #                      GNU General Public License                       #
//  #                                                                       #
//  #     This program is free software; you can redistribute it and/or     #
//  #    modify it under the terms of the GNU General Public License as     #
//  #    published by the Free Software Foundation; either version 3 of     #
//  #          the License, or at your option any later version.            #
//  #                                                                       #
//  #  This program is distributed in the hope that it will be useful, but  #
//  #       WITHOUT ANY WARRANTY; without even the implied warranty of      #
//  #          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.         #
//  #          See the GNU General Public License for more details.         #
//  #                                                                       #
//  #        You should have received a copy of the GNU General Public      #
//  #              License along with this program. If not, see             #
//  #                    <https://www.gnu.org/licenses/>.                   #
//  #                                                                       #
//  #                    Copyright 2024 Federico Lencina                    #
// #=========================================================================#

package com.fls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main implements Interface {

    private static class ProcessReader implements Callable {
        private InputStream inputStream;

        public ProcessReader(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public Object call() throws Exception {
            return new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.toList());
        }
    }

    @Override
    public void onPrimaryCheckBox(boolean isPrimarySelected) {
        boolean isLinux = System.getProperty("os.name").toLowerCase().startsWith("linux");

        ProcessBuilder builder = new ProcessBuilder();
        if (!isLinux) {
            builder.command(System.getProperty("user.dir") + "\\src\\scripts\\windows\\primary.bat");
        } else {
            builder.command("sh", "-c", System.getProperty("user.dir") + "/src/scripts/linux/primary.sh");
        }

        ExecutorService pool = Executors.newSingleThreadExecutor();

        try {
            Process process = builder.start();
            ProcessReader task = new ProcessReader(process.getInputStream());
            Future<List<String>> future = pool.submit(task);

            List<String> results = future.get();
            for (String res : results) {
                    System.out.println(res);
            }
            int exitCode = process.waitFor();

            System.out.println("Exit code:" + exitCode);
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }


    @Override
    public void onDefaultCheckbox(boolean isDefaultSelected) {
        boolean isLinux = System.getProperty("os.name").toLowerCase().startsWith("linux");

        ProcessBuilder builder = new ProcessBuilder();
        if (!isLinux) {
            builder.command(System.getProperty("user.dir") + "\\src\\scripts\\windows\\default.bat");
        } else {
            builder.command("sh", "-c", System.getProperty("user.dir") + "/src/scripts/linux/default.sh");
        }

        ExecutorService pool = Executors.newSingleThreadExecutor();

        try {
            Process process = builder.start();
            ProcessReader task = new ProcessReader(process.getInputStream());
            Future<List<String>> future = pool.submit(task);

            List<String> results = future.get();
            for (String res : results) {
                System.out.println(res);
            }
            int exitCode = process.waitFor();

            System.out.println("Exit code:" + exitCode);
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}

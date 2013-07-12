package org.neo4j.shell.tools;

import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.shell.ShellClient;
import org.neo4j.shell.ShellException;
import org.neo4j.shell.impl.CollectingOutput;

import java.rmi.RemoteException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by mh on 12.07.13.
 */
public class Asserts {
    public static void assertCommand(ShellClient client, String command, String... expected) throws RemoteException, ShellException {
        CollectingOutput out = new CollectingOutput();
        client.evaluate(command, out);
        Iterator<String> it = out.iterator();
        assertEquals(expected.length, IteratorUtil.count(out.iterator()));
        for (String s : expected) {
            String output = it.next().trim();
            if (s.isEmpty()) continue;
            assertEquals(s, output);
        }
    }
}

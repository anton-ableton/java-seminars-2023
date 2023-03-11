package com.github.artfly.jdu;

import com.github.artfly.jdu.core.DuTest;
import com.github.artfly.jdu.model.DuFile;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.artfly.jdu.core.DuTreeElement.*;

public class DuTreeBuilderTest extends DuTest {

    @Test
    public void testOneFileInDirectory() throws IOException {
        FileSystem fs = fileSystem();
        Path fooPath = fs.getPath("/foo");
        Files.createDirectory(fooPath);
        Path barPath = fooPath.resolve("bar.txt");
        Files.createFile(barPath);

        DuFile actual = DuTreeBuilder.build(fooPath);
        DuFile expected = tree(fs, dir("foo", file("bar.txt")));
        TestCase.assertEquals(expected, actual);
    }

}

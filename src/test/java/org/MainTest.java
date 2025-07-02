
package org;

import org.junit.jupiter.api.Test;

class MainIntegrationTest {
    @Test
    void testMainWithArgs() {
        String[] args = {"-o", "output", "-s", "input.txt"};
        Main.main(args);
    }
}
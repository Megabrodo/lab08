package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

//import static org.junit.jupiter.api.Assertions.fail;
import static java.lang.Thread.sleep;

import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static final long SLEEP_TIME_CAUSE = 100L;
    private static final long SLEEP_TIME_DETAILS = 6100L;
    private final DeathNoteImpl deathnote = new DeathNoteImpl();

    @Test
    public void testRules() { // NOPMD Needed for the assignment
        try {
            deathnote.getRule(0);
            //fail("Error: no rule 0");
        } catch (final IllegalArgumentException e) {
            assertNotNull(e);
            assertFalse(e.getMessage().isBlank());
        }

        try {
            deathnote.getRule(-1);
            //fail("Error: no negative rule");
        } catch (final IllegalArgumentException e) {
            assertNotNull(e);
            assertFalse(e.getMessage().isBlank());
        }
    }

    @Test
    public void testEmptyRules() { // NOPMD Needed for the assignment
        for (int i = 1; i <= DeathNote.RULES.size(); i++) {
            final var rule = deathnote.getRule(i);
            assertNotNull(rule);
            assertFalse(rule.isBlank());
        }
        //fail("Error: no empty rules");
    }

    @Test
    public void testDeath() { // NOPMD Needed for the assignment
        final String name = "Donald Trump";
        assertFalse(deathnote.isNameWritten(name));
        deathnote.writeName(name);
        assertTrue(deathnote.isNameWritten(name));
        assertFalse(deathnote.isNameWritten("Danilo Pianini"));
        assertFalse(deathnote.isNameWritten(""));
        //fail("Name not in deathnote");
    }

    @Test
    public void testCause() throws InterruptedException { // NOPMD Needed for the assignment
        assertThrows(IllegalStateException.class, () -> deathnote.writeDeathCause("Killed himself"));
        /* Potential different implementation (which violates PMD)
         * try {
         *      deathnote.writeDeathCause("Killed himself");
         * } catch {}
         */
        final String name1 = "Vladimir Putin";
        deathnote.writeName(name1);
        assertEquals("Heart attack", deathnote.getDeathCause(name1));
        final String name2 = "L";
        deathnote.writeName(name2);
        assertTrue(deathnote.writeDeathCause("karting accident"));
        assertEquals("karting accident", deathnote.getDeathCause(name2));
        sleep(SLEEP_TIME_CAUSE);
        assertFalse(deathnote.writeDeathCause("Hanged himself"));
        //fail("Error");
    }

    @Test
    public void testDetails() throws InterruptedException { // NOPMD Needed for the assignment
        assertThrows(IllegalStateException.class, () -> deathnote.writeDetails("He got pranked by Jonh"));
        /* Potential different implementation (which violates PMD)
         * try {
         *      deathnote.writeDetails("He got pranked by Jonh");
         * } catch {}
         */
        final String name1 = "Tom Howard";
        deathnote.writeName(name1);
        assertEquals("", deathnote.getDeathDetails(name1));
        assertTrue(deathnote.writeDetails("ran for too long"));
        assertEquals("ran for too long", deathnote.getDeathDetails(name1));
        final String name2 = "L";
        deathnote.writeName(name2);
        sleep(SLEEP_TIME_DETAILS);
        assertFalse(deathnote.writeDetails("My twisted humor made him laugh"));
        //fail("Error");
    }
}

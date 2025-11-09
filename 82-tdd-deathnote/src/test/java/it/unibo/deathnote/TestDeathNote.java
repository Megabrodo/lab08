package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static java.lang.Thread.sleep;

import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    DeathNoteImpl deathnote = new DeathNoteImpl();
    private static final long SLEEP_TIME_CAUSE = 100L;
    private static final long SLEEP_TIME_DETAILS = 6100L;

    @Test
    public void testRules(){
        try {
            deathnote.getRule(0);
            fail("Error: no rule 0");
        }
        catch (final IllegalArgumentException e){
            assertNotNull(e);
            assertFalse(e.getMessage().isBlank());
        }

        try {
            deathnote.getRule(-1);
            fail("Error: no negative rule");
        }
        catch (final IllegalArgumentException e){
            assertNotNull(e);
            assertFalse(e.getMessage().isBlank());
        }
    }

    @Test
    public void testEmptyRules(){
        for (int i=1; i<=DeathNote.RULES.size(); i++){
            final var rule = deathnote.getRule(i);
            assertNotNull(rule);
            assertFalse(rule.isBlank());
        }
        fail("Error: no empty rules");
    }

    @Test
    public void testDeath(){
        final String name = "Donald Trump";
        assertFalse(deathnote.isNameWritten(name));
        deathnote.writeName(name);
        assertTrue(deathnote.isNameWritten(name));
        assertFalse(deathnote.isNameWritten("Danilo Pianini"));
        assertFalse(deathnote.isNameWritten(""));
        fail("Name not in deathnote");
    }

    @Test
    public void testCause() throws InterruptedException {
        try{
            deathnote.writeDeathCause("Shat himself");
        }
        catch(final IllegalStateException e){}
        final String name1 = "Donald Trump";
        deathnote.writeName(name1);
        assertEquals("Heart attack", deathnote.getDeathCause(name1));
        final String name2 = "L";
        deathnote.writeName(name2);
        assertTrue(deathnote.writeDeathCause("karting accident"));
        assertEquals("karting accident", deathnote.getDeathCause(name2));
        sleep(SLEEP_TIME_CAUSE);
        assertFalse(deathnote.writeDeathCause("Hanged himself"));
        fail("Error");
    }

    @Test
    public void testDetails() throws InterruptedException {
        try{
            deathnote.writeDetails("He got pranked by John");
        }
        catch(final IllegalStateException e){}
        final String name1 = "Donald Trump";
        deathnote.writeName(name1);
        assertEquals("", deathnote.getDeathDetails(name1));
        assertTrue(deathnote.writeDetails("ran for too long"));
        assertEquals("ran for too long", deathnote.getDeathDetails(name1));
        final String name2 = "L";
        deathnote.writeName(name2);
        sleep(SLEEP_TIME_DETAILS);
        assertFalse(deathnote.writeDetails("My twisted humor made him laugh"));
        fail("Error");
    }
}
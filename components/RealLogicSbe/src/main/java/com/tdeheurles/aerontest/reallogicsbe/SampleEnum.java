/* Generated SBE (Simple Binary Encoding) message codec. */
package com.tdeheurles.aerontest.reallogicsbe;

public enum SampleEnum
{
    VALUE_1(1),

    VALUE_2(2),

    VALUE_3(3),

    /**
     * To be used to represent not present or null.
     */
    NULL_VAL(-2147483648);

    private final int value;

    SampleEnum(final int value)
    {
        this.value = value;
    }

    /**
     * The raw encoded value in the Java type representation.
     *
     * @return the raw value encoded.
     */
    public int value()
    {
        return value;
    }

    /**
     * Lookup the enum value representing the value.
     *
     * @param value encoded to be looked up.
     * @return the enum value representing the value.
     */
    public static SampleEnum get(final int value)
    {
        switch (value)
        {
            case 1: return VALUE_1;
            case 2: return VALUE_2;
            case 3: return VALUE_3;
            case -2147483648: return NULL_VAL;
        }

        throw new IllegalArgumentException("Unknown value: " + value);
    }
}

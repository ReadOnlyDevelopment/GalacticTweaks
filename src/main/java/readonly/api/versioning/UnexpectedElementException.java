/*
 * This file is part of GalacticGradle, licensed under the MIT License (MIT).
 *
 * Copyright (c) TeamGalacticraft <https://galacticraft.net>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package readonly.api.versioning;

import java.util.Arrays;

import readonly.api.versioning.ElementStream.ElementType;

/**
 * Thrown when attempting to consume a stream element of unexpected types.
 *
 * @see ElementStream#consume(ElementStream.ElementType...)
 */
public class UnexpectedElementException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    /**
     * The unexpected element in the stream.
     */
    private final Object unexpected;

    /**
     * The position of the unexpected element in the stream.
     */
    private final int position;

    /**
     * The array of the expected element types.
     */
    private final ElementType<?>[] expected;

    /**
     * Constructs a {@code UnexpectedElementException} instance with the unexpected element and the expected types.
     *
     * @param element the unexpected element in the stream
     * @param position the position of the unexpected element
     * @param expected an array of the expected element types
     */
    UnexpectedElementException(Object element, int position, ElementType<?>... expected)
    {
        unexpected = element;
        this.position = position;
        this.expected = expected;
    }

    /**
     * Gets the unexpected element.
     *
     * @return the unexpected element
     */
    public Object getUnexpectedElement()
    {
        return unexpected;
    }

    /**
     * Gets the position of the unexpected element.
     *
     * @return the position of the unexpected element
     */
    public int getPosition()
    {
        return position;
    }

    /**
     * Gets the expected element types.
     *
     * @return an array of expected element types
     */
    public ElementType<?>[] getExpectedElementTypes()
    {
        return expected;
    }

    /**
     * Returns the string representation of this exception containing the information about the unexpected element and,
     * if available, about the expected types.
     *
     * @return the string representation of this exception
     */
    @Override
    public String toString()
    {
        String message = String.format("Unexpected element '%s' at position '%d'", unexpected, position);
        if (expected.length > 0) {
            message += String.format(", expecting '%s'", Arrays.toString(expected));
        }
        return message;
    }
}

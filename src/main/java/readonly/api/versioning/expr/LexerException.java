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

package readonly.api.versioning.expr;

import readonly.api.versioning.ParseException;

/**
 * Thrown during the lexical analysis when
 * an illegal character is encountered.
 */
public class LexerException extends ParseException {

    private static final long serialVersionUID = 1L;
    /**
     * The string being analyzed starting from an illegal character.
     */
    private final String expr;

    /**
     * Constructs a {@code LexerException} instance with
     * a string starting from an illegal character.
     *
     * @param expr the string starting from an illegal character
     */
    LexerException(String expr) {
        this.expr = expr;
    }

    /**
     * Returns the string representation of this exception.
     *
     * @return the string representation of this exception
     */
    @Override
    public String toString() {
        return "Illegal character near '" + expr + "'";
    }
}

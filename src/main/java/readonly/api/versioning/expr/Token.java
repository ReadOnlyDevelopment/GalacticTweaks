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

import java.util.regex.Pattern;

import readonly.api.versioning.ElementStream;

/**
 * This class holds the information about lexemes in the input stream.
 */
class Token {

    /**
     * Valid token types.
     */
    static enum Type implements ElementStream.ElementType<Token> {

        NUMERIC("0|[1-9][0-9]*"),
        DOT("\\."),
        HYPHEN("-"),
        EQUAL("="),
        NOT_EQUAL("!="),
        GREATER(">(?!=)"),
        GREATER_EQUAL(">="),
        LESS("<(?!=)"),
        LESS_EQUAL("<="),
        TILDE("~"),
        WILDCARD("[\\*xX]"),
        CARET("\\^"),
        AND("&"),
        OR("\\|"),
        NOT("!(?!=)"),
        LEFT_PAREN("\\("),
        RIGHT_PAREN("\\)"),
        WHITESPACE("\\s+"),
        EOI("?!");

        /**
         * A pattern matching this type.
         */
        final Pattern pattern;

        /**
         * Constructs a token type with a regular
         * expression for the pattern.
         *
         * @param regexp the regular expression for the pattern
         * @see #pattern
         */
        private Type(String regexp) {
            pattern = Pattern.compile("^(" + regexp + ")");
        }

        /**
         * Returns the string representation of this type.
         *
         * @return the string representation of this type
         */
        @Override
        public String toString() {
            return name() + "(" + pattern + ")";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isMatchedBy(Token token) {
            if (token == null) {
                return false;
            }
            return this == token.type;
        }
    }

    /**
     * The type of this token.
     */
    final Type type;

    /**
     * The lexeme of this token.
     */
    final String lexeme;

    /**
     * The position of this token.
     */
    final int position;

    /**
     * Constructs a {@code Token} instance
     * with the type, lexeme and position.
     *
     * @param type the type of this token
     * @param lexeme the lexeme of this token
     * @param position the position of this token
     */
    Token(Type type, String lexeme, int position) {
        this.type = type;
        this.lexeme = (lexeme == null) ? "" : lexeme;
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Token)) {
            return false;
        }
        Token token = (Token) other;
        return
            type.equals(token.type) &&
            lexeme.equals(token.lexeme) &&
            position == token.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + type.hashCode();
        hash = 71 * hash + lexeme.hashCode();
        hash = 71 * hash + position;
        return hash;
    }

    /**
     * Returns the string representation of this token.
     *
     * @return the string representation of this token
     */
    @Override
    public String toString() {
        return String.format(
            "%s(%s) at position %d",
            type.name(),
            lexeme, position
        );
    }
}
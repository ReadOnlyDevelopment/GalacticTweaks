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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import readonly.api.versioning.ElementStream;
import readonly.api.versioning.Version;

public class Expressions
{
	/**
	 * Expression for the logical "and" operator.
	 */
	static class And implements Expression
	{

		/**
		 * The left-hand operand of expression.
		 */
		private final Expression left;

		/**
		 * The right-hand operand of expression.
		 */
		private final Expression right;

		/**
		 * Constructs a {@code And} expression with
		 * the left-hand and right-hand operands.
		 *
		 * @param left
		 *            the left-hand operand of expression
		 * @param right
		 *            the right-hand operand of expression
		 */
		And(Expression left, Expression right)
		{
			this.left = left;
			this.right = right;
		}

		/**
		 * Checks if both operands evaluate to {@code true}.
		 *
		 * @param version
		 *            the version to interpret against
		 * 
		 * @return {@code true} if both operands evaluate to {@code true}
		 *         or {@code false} otherwise
		 */
		@Override
		public boolean interpret(Version version)
		{
			return left.interpret(version) && right.interpret(version);
		}
	}

	/**
	 * Expression for the comparison "equal" operator.
	 */
	static class Equal implements Expression
	{

		/**
		 * The parsed version, the right-hand operand of the "equal" operator.
		 */
		private final Version parsedVersion;

		/**
		 * Constructs a {@code Equal} expression with the parsed version.
		 *
		 * @param parsedVersion
		 *            the parsed version
		 */
		Equal(Version parsedVersion)
		{
			this.parsedVersion = parsedVersion;
		}

		/**
		 * Checks if the current version equals the parsed version.
		 *
		 * @param version
		 *            the version to compare to, the left-hand
		 *            operand of the "equal" operator
		 * 
		 * @return {@code true} if the version equals the
		 *         parsed version or {@code false} otherwise
		 */
		@Override
		public boolean interpret(Version version)
		{
			return version.equals(parsedVersion);
		}
	}

	/**
	 * Expression for the comparison "greater than" operator.
	 */
	static class Greater implements Expression
	{

		/**
		 * The parsed version, the right-hand
		 * operand of the "greater than" operator.
		 */
		private final Version parsedVersion;

		/**
		 * Constructs a {@code Greater} expression with the parsed version.
		 *
		 * @param parsedVersion
		 *            the parsed version
		 */
		Greater(Version parsedVersion)
		{
			this.parsedVersion = parsedVersion;
		}

		/**
		 * Checks if the current version is greater than the parsed version.
		 *
		 * @param version
		 *            the version to compare to, the left-hand
		 *            operand of the "greater than" operator
		 * 
		 * @return {@code true} if the version is greater than the
		 *         parsed version or {@code false} otherwise
		 */
		@Override
		public boolean interpret(Version version)
		{
			return version.greaterThan(parsedVersion);
		}
	}

	/**
	 * Expression for the comparison "greater than or equal to" operator.
	 */
	static class GreaterOrEqual implements Expression
	{

		/**
		 * The parsed version, the right-hand operand
		 * of the "greater than or equal to" operator.
		 */
		private final Version parsedVersion;

		/**
		 * Constructs a {@code GreaterOrEqual} expression with the parsed version.
		 *
		 * @param parsedVersion
		 *            the parsed version
		 */
		GreaterOrEqual(Version parsedVersion)
		{
			this.parsedVersion = parsedVersion;
		}

		/**
		 * Checks if the current version is greater
		 * than or equal to the parsed version.
		 *
		 * @param version
		 *            the version to compare to, the left-hand operand
		 *            of the "greater than or equal to" operator
		 * 
		 * @return {@code true} if the version is greater than or equal
		 *         to the parsed version or {@code false} otherwise
		 */
		@Override
		public boolean interpret(Version version)
		{
			return version.greaterThanOrEqualTo(parsedVersion);
		}
	}

	/**
	 * Expression for the comparison "less than" operator.
	 */
	static class Less implements Expression
	{

		/**
		 * The parsed version, the right-hand
		 * operand of the "less than" operator.
		 */
		private final Version parsedVersion;

		/**
		 * Constructs a {@code Less} expression with the parsed version.
		 *
		 * @param parsedVersion
		 *            the parsed version
		 */
		Less(Version parsedVersion)
		{
			this.parsedVersion = parsedVersion;
		}

		/**
		 * Checks if the current version is less than the parsed version.
		 *
		 * @param version
		 *            the version to compare to, the left-hand
		 *            operand of the "less than" operator
		 * 
		 * @return {@code true} if the version is less than the
		 *         parsed version or {@code false} otherwise
		 */
		@Override
		public boolean interpret(Version version)
		{
			return version.lessThan(parsedVersion);
		}
	}

	/**
	 * Expression for the comparison "less than or equal to" operator.
	 */
	static class LessOrEqual implements Expression
	{

		/**
		 * The parsed version, the right-hand operand
		 * of the "less than or equal to" operator.
		 */
		private final Version parsedVersion;

		/**
		 * Constructs a {@code LessOrEqual} expression with the parsed version.
		 *
		 * @param parsedVersion
		 *            the parsed version
		 */
		LessOrEqual(Version parsedVersion)
		{
			this.parsedVersion = parsedVersion;
		}

		/**
		 * Checks if the current version is less
		 * than or equal to the parsed version.
		 *
		 * @param version
		 *            the version to compare to, the left-hand operand
		 *            of the "less than or equal to" operator
		 * 
		 * @return {@code true} if the version is less than or equal
		 *         to the parsed version or {@code false} otherwise
		 */
		@Override
		public boolean interpret(Version version)
		{
			return version.lessThanOrEqualTo(parsedVersion);
		}
	}

	/**
	 * A lexer for the SemVer Expressions.
	 */
	static class Lexer
	{

		/**
		 * Constructs a {@code Lexer} instance.
		 */
		Lexer()
		{
		}

		/**
		 * Tokenizes the specified input string.
		 *
		 * @param input
		 *            the input string to tokenize
		 * 
		 * @return a stream of tokens
		 * 
		 * @throws LexerException
		 *             when encounters an illegal character
		 */
		ElementStream<Token> tokenize(String input)
		{
			List<Token>	tokens		= new ArrayList<Token>();
			int			tokenPos	= 0;
			while (!input.isEmpty())
			{
				boolean matched = false;
				for (Token.Type tokenType : Token.Type.values())
				{
					Matcher matcher = tokenType.pattern.matcher(input);
					if (matcher.find())
					{
						matched = true;
						input = matcher.replaceFirst("");
						if (tokenType != Token.Type.WHITESPACE)
						{
							tokens.add(new Token(tokenType, matcher.group(), tokenPos));
						}
						tokenPos += matcher.end();
						break;
					}
				}
				if (!matched)
				{
					throw new LexerException(input);
				}
			}
			tokens.add(new Token(Token.Type.EOI, null, tokenPos));
			return new ElementStream<Token>(tokens.toArray(new Token[tokens.size()]));
		}
	}

	/**
	 * Expression for the logical "negation" operator.
	 */
	static class Not implements Expression
	{

		/**
		 * The expression to negate.
		 */
		private final Expression expr;

		/**
		 * Constructs a {@code Not} expression with an expression to negate.
		 *
		 * @param expr
		 *            the expression to negate
		 */
		Not(Expression expr)
		{
			this.expr = expr;
		}

		/**
		 * Negates the given expression.
		 *
		 * @param version
		 *            the version to interpret against
		 * 
		 * @return {@code true} if the given expression evaluates to
		 *         {@code false} and {@code false} otherwise
		 */
		@Override
		public boolean interpret(Version version)
		{
			return !expr.interpret(version);
		}
	}

	/**
	 * Expression for the comparison "not equal" operator.
	 */
	static class NotEqual implements Expression
	{

		/**
		 * The parsed version, the right-hand operand of the "not equal" operator.
		 */
		private final Version parsedVersion;

		/**
		 * Constructs a {@code NotEqual} expression with the parsed version.
		 *
		 * @param parsedVersion
		 *            the parsed version
		 */
		NotEqual(Version parsedVersion)
		{
			this.parsedVersion = parsedVersion;
		}

		/**
		 * Checks if the current version does not equal the parsed version.
		 *
		 * @param version
		 *            the version to compare with, the left-hand
		 *            operand of the "not equal" operator
		 * 
		 * @return {@code true} if the version does not equal the
		 *         parsed version or {@code false} otherwise
		 */
		@Override
		public boolean interpret(Version version)
		{
			return !version.equals(parsedVersion);
		}
	}

	/**
	 * Expression for the logical "or" operator.
	 */
	static class Or implements Expression
	{

		/**
		 * The left-hand operand of expression.
		 */
		private final Expression left;

		/**
		 * The right-hand operand of expression.
		 */
		private final Expression right;

		/**
		 * Constructs a {@code Or} expression with
		 * the left-hand and right-hand operands.
		 *
		 * @param left
		 *            the left-hand operand of expression
		 * @param right
		 *            the right-hand operand of expression
		 */
		Or(Expression left, Expression right)
		{
			this.left = left;
			this.right = right;
		}

		/**
		 * Checks if one of the operands evaluates to {@code true}.
		 *
		 * @param version
		 *            the version to interpret against
		 * 
		 * @return {@code true} if one of the operands evaluates to {@code true}
		 *         or {@code false} otherwise
		 */
		@Override
		public boolean interpret(Version version)
		{
			return left.interpret(version) || right.interpret(version);
		}
	}

}

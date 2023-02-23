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
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code NormalVersion} class represents the version core. This class is immutable and hence thread-safe.
 */
class NormalVersion implements Comparable<NormalVersion>
{

    /**
     * Null version, the implementation of the Null Object design pattern.
     */
    static final NullNormalVersion NULL = new NullNormalVersion();

    /**
     * The implementation of the Null Object design pattern.
     */
    private static class NullNormalVersion extends NormalVersion
    {

        /**
         * Constructs a {@code NullNormalVersion} instance.
         */
        NullNormalVersion()
        {
            super(0, 0, 0);
        }

        /**
         * @throws UnsupportedOperationException as Null version cannot be incremented
         */
        @Override
        NormalVersion incrementMajor()
        {
            throw new UnsupportedOperationException("Version is Null");
        }

        /**
         * @throws UnsupportedOperationException as Null version cannot be incremented
         */
        @Override
        NormalVersion incrementMinor()
        {
            throw new UnsupportedOperationException("Version is Null");
        }

        /**
         * @throws UnsupportedOperationException as Null version cannot be incremented
         */
        @Override
        NormalVersion incrementPatch()
        {
            throw new UnsupportedOperationException("Version is Null");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString()
        {
            return super.toString();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode()
        {
            return 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object other)
        {
            return other instanceof NullNormalVersion;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int compareTo(NormalVersion other)
        {
            if (!equals(other)) {
                /**
                 * Pre-release versions have a lower precedence than the associated normal version. (SemVer p.9)
                 */
                return 1;
            }
            return 0;
        }
    }

    /**
     * The major version number.
     */
    private final Integer major;

    /**
     * The minor version number.
     */
    private final Integer minor;

    /**
     * The patch version number.
     */
    private final Integer patch;

    private final List<Integer> remaining = new LinkedList<>();

    /**
     * Constructs a {@code NormalVersion} with the major, minor and patch version numbers.
     *
     * @param major the major version number
     * @param minor the minor version number
     * @param patch the patch version number
     * @throws IllegalArgumentException if one of the version numbers is a negative integer
     */
    NormalVersion(Integer major, Integer minor, Integer patch)
    {
        if (major < 0 || minor < 0 || patch < 0) {
            throw new IllegalArgumentException("Major, minor and patch versions MUST be non-negative integers.");
        }
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    NormalVersion addRemainingIntegers(Integer... integers)
    {
        this.remaining.addAll(Arrays.asList(integers));
        return this;
    }

    /**
     * Returns the major version number.
     *
     * @return the major version number
     */
    Integer getMajor()
    {
        return major;
    }

    /**
     * Returns the minor version number.
     *
     * @return the minor version number
     */
    Integer getMinor()
    {
        return minor;
    }

    /**
     * Returns the patch version number.
     *
     * @return the patch version number
     */
    Integer getPatch()
    {
        return patch;
    }

    /**
     * Increments the major version number.
     *
     * @return a new instance of the {@code NormalVersion} class
     */
    NormalVersion incrementMajor()
    {
        return new NormalVersion(major + 1, 0, 0);
    }

    /**
     * Increments the minor version number.
     *
     * @return a new instance of the {@code NormalVersion} class
     */
    NormalVersion incrementMinor()
    {
        return new NormalVersion(major, minor + 1, 0);
    }

    /**
     * Increments the patch version number.
     *
     * @return a new instance of the {@code NormalVersion} class
     */
    NormalVersion incrementPatch()
    {
        return new NormalVersion(major, minor, patch + 1);
    }

    int getRemainingTotal()
    {
        return remaining.stream().mapToInt(Integer::valueOf).sum();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(NormalVersion other)
    {
        int result = major - other.major;
        if (result == 0) {
            result = minor - other.minor;
            if (result == 0) {
                result = patch - other.patch;
                if (result == 0) {
                    result = getRemainingTotal() - other.getRemainingTotal();
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other)
    {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NormalVersion)) {
            return false;
        }
        return compareTo((NormalVersion) other) == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        int hash = 17;
        hash = 31 * hash + major;
        hash = 31 * hash + minor;
        hash = 31 * hash + patch;
        for (int i : remaining)
            hash = 31 * hash + i;
        return hash;
    }

    /**
     * Returns the string representation of this normal version. A normal version number MUST take the form X.Y.Z where
     * X, Y, and Z are non-negative integers. X is the major version, Y is the minor version, and Z is the patch
     * version. (SemVer p.2)
     *
     * @return the string representation of this normal version
     */
    @Override
    public String toString()
    {
        if (remaining.isEmpty())
            return String.format("%d.%d.%d", major, minor, patch);
        else
            return String.format("%d.%d.%d.%s", major, minor, patch,
                String.join(".", remaining.stream().map(String::valueOf).collect(Collectors.toList())));
    }
}

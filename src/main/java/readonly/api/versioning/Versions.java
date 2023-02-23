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

import java.util.List;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Versions
{
    public static VersionSet empty()
    {
        return new VersionSet();
    }

    @SafeVarargs
    public static VersionSet of(String... versions)
    {
        return new VersionSet(versions);
    }

    public static VersionSet of(List<String> versionsList)
    {
        return new VersionSet(versionsList.stream().map(toVersion).collect(Collectors.toList()));
    }

    public static VersionSet set(TreeSet<Version> set)
    {
        return new VersionSet(set);
    }

    public static Function<String, Version> toVersion = Version::of;
}

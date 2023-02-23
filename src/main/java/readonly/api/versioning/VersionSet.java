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
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class VersionSet extends TreeSet<Version> implements SortedSet<Version>
{
	private static final long serialVersionUID = 1L;

	public static VersionCollector collectVersions()
	{
		return new VersionCollector();
	}
	
	public VersionSet()
	{
		super(Version.BUILD_AWARE_ORDER);
	}

	public VersionSet(Comparator<Version> comparator)
	{
		super(comparator);
	}

	public VersionSet(Collection<Version> collection)
	{
		super(collection);
	}

	@SafeVarargs
	public VersionSet(Version... versions)
	{
		this(Arrays.asList(versions));
	}

	@SafeVarargs
	public VersionSet(String... versions)
	{
		this(Arrays.asList(versions).stream().map(Version::of).collect(Collectors.toList()));
	}

	public Version[] asArray()
	{
		return this.toArray(new Version[this.size()]);
	}

	public VersionSet extractSnapshots()
	{
		return new VersionSet(this.stream().filter(Version::isSnapshotVersion).collect(collectVersions()));
	}
	
	public VersionSet extractReleases()
	{
		return new VersionSet(this.stream().filter(Version::isStable).collect(collectVersions()));
	}

	@Override
	public SortedSet<Version> subSet(Version fromElement, Version toElement)
	{
		return new VersionSet(super.subSet(fromElement, toElement));
	}

	public SortedSet<Version> versionsUpTo(Version toElement)
	{
		return new VersionSet(super.headSet(toElement));
	}

	public SortedSet<Version> versionsFrom(Version fromElement)
	{
		return new VersionSet(super.tailSet(fromElement));
	}

	public Version oldest()
	{
		return super.first();
	}

	public Version latest()
	{
		return super.last();
	}
	
	public Version getVersion(String version)
	{
		return getVersion(Version.of(version));
	}
	
	public Version getVersion(Version version)
	{
		for(Version v : this)
		{
			if(v.equals(version))
			{
				return v;
			}
		}
		
		return Version.Null();
	}

	@Override
	public String toString()
	{
		return super.toString();
	}

	public static class VersionCollector implements Collector<Version, TreeSet<Version>, TreeSet<Version>>
	{
		@Override
		public Supplier<TreeSet<Version>> supplier()
		{
			return VersionSet::new;
		}

		@Override
		public BiConsumer<TreeSet<Version>, Version> accumulator()
		{
			return (list, object) -> list.add(object);
		}

		@Override
		public BinaryOperator<TreeSet<Version>> combiner()
		{
	        return (l1, l2) ->
	        {
	            l1.addAll(l2);
	            return l1;
	        };
		}

		@Override
		public Function<TreeSet<Version>, TreeSet<Version>> finisher()
		{
			return Versions::set;
		}

		@Override
		public Set<Characteristics> characteristics()
		{
			return EnumSet.of(Characteristics.CONCURRENT);
		}
	}
}

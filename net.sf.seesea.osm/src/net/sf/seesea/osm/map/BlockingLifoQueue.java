/**
 * 
 Copyright (c) 2010-2012, Jens Kübler All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the <organization> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.sf.seesea.osm.map;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public final class BlockingLifoQueue<T> implements BlockingQueue<T>
{
  // we add and remove only from the end of the queue
  private final BlockingDeque<T> deque; 

  public BlockingLifoQueue()
  { deque = new LinkedBlockingDeque<T>(); }

  public boolean add(T e) {
    deque.addLast(e);
    return true;
  }

  public boolean contains(Object o)
  { return deque.contains(o); }

  public int drainTo(Collection<? super T> c)
  { return deque.drainTo(c); }

  public int drainTo(Collection<? super T> c, int maxElements)
  { return deque.drainTo(c,maxElements); }

  public boolean offer(T e)
  { return deque.offerLast(e); }

  public boolean offer(T e, long timeout, TimeUnit unit)
      throws InterruptedException
  { return deque.offerLast(e,timeout,unit); }

  public T poll(long timeout, TimeUnit unit) throws InterruptedException
  { return deque.pollLast(timeout, unit); }

  public void put(T e) throws InterruptedException
  { deque.putLast(e); }

  public int remainingCapacity()
  { return deque.size(); }

  public boolean remove(Object o)
  { return deque.remove(o); }

  public T take() throws InterruptedException
  { return deque.takeLast(); }

  public T element()
  {
    if (deque.isEmpty()) { 
      throw new NoSuchElementException("empty stack");
    }

    return deque.pollLast();
  }

  public T peek()
  { return deque.peekLast(); }

  public T poll()
  { return deque.peekLast(); }

  public T remove()
  {
    if (deque.isEmpty()) { 
      throw new NoSuchElementException("empty stack");
    }

    return deque.pollLast();
  }

  public boolean addAll(Collection<? extends T> c)
  { 
    for (T e : c) { deque.add(e); }
    return true;
  }

  public void clear()
  { deque.clear();}

  public boolean containsAll(Collection<?> c)
  { return deque.containsAll(c); }

  public boolean isEmpty()
  {  return deque.isEmpty(); }

  public Iterator<T> iterator()
  { return deque.descendingIterator(); }

  public boolean removeAll(Collection<?> c)
  { return deque.removeAll(c); }

  public boolean retainAll(Collection<?> c)
  { return deque.retainAll(c); }

  public int size()
  { return deque.size(); }

  public Object[] toArray()
  { return deque.toArray(); }

  public <T> T[] toArray(T[] a)
  { return deque.toArray(a); }
}

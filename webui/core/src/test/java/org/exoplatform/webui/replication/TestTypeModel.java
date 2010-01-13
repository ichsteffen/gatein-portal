/*
 * Copyright (C) 2009 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.exoplatform.webui.replication;

import junit.framework.TestCase;
import org.exoplatform.webui.application.replication.model.*;

import java.io.Serializable;
import java.util.Map;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
public class TestTypeModel extends TestCase
{


   public void testBar()
   {
      TypeDomain domain = new TypeDomain();
      assertEquals(0, domain.getSize());
   }

   public void testFoo()
   {
      TypeDomain domain = new TypeDomain();
      assertType(String.class, domain.add(String.class));
      assertEquals(1, domain.getSize());
      assertType(String.class, domain.getTypeModel(String.class));
   }

   public void testJuu()
   {
      TypeDomain domain = new TypeDomain();
      ClassTypeModel aTM = (ClassTypeModel) domain.add(A.class);
      assertEquals(4, domain.getSize());
      assertEquals(A.class.getName(), aTM.getName());
      assertEquals(SetBuilder.create(domain.getTypeModel(int.class)).with(aTM).with(domain.getTypeModel(boolean.class)).build(domain.getTypeModel(String.class)), domain.getTypeModels());
      Map<String, FieldModel> fieldMap = aTM.getFieldMap();
      assertEquals(3, fieldMap.size());
      FieldModel aFM = fieldMap.get("a");
      assertEquals("a", aFM.getName());
      assertEquals(domain.getTypeModel(String.class), aFM.getType());
      FieldModel bFM = fieldMap.get("b");
      assertEquals("b", bFM.getName());
      assertEquals(domain.getTypeModel(int.class), bFM.getType());
      FieldModel cFM = fieldMap.get("c");
      assertEquals("c", cFM.getName());
      assertEquals(domain.getTypeModel(boolean.class), cFM.getType());
   }

   private void assertType(Class<? extends Serializable> javaType, TypeModel typeModel)
   {
      assertTrue(typeModel instanceof SerializableTypeModel);
      SerializableTypeModel serializableTypeModel = (SerializableTypeModel)typeModel;
      assertEquals(javaType, serializableTypeModel.getJavaType());
   }

}

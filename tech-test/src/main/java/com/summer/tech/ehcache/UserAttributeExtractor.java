package com.summer.tech.ehcache;

import com.summer.tech.memcached.User;

import net.sf.ehcache.Element;
import net.sf.ehcache.search.attribute.AttributeExtractor;
import net.sf.ehcache.search.attribute.AttributeExtractorException;

public class UserAttributeExtractor implements AttributeExtractor {

   @Override
   public Object attributeFor(Element element, String attributeName)
         throws AttributeExtractorException {
      User user = (User) element.getObjectValue();
      return user.getName();
   }

}
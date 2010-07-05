/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartitengineering.demo.roa.domains;

import com.smartitengineering.domain.AbstractPersistentDTO;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author imyousuf
 */
public class Author extends AbstractPersistentDTO<Author> {

  private String nationalId;
  private String name;
  private String uniqueNickname;

  @Override
  public boolean isValid() {
    return StringUtils.isNotBlank(uniqueNickname) && StringUtils.isNotBlank(name);
  }
}

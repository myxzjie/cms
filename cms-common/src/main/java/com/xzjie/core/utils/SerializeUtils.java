package com.xzjie.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializeUtils {

	private static final Logger LOG = LoggerFactory.getLogger(SerializeUtils.class);

	public static byte[] serialize(Object value) {
		if (value == null) {
			throw new NullPointerException("Can't serialize null");
		}
		byte[] rv = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(value);
			os.close();
			bos.close();
			rv = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("serialize error");
		} finally {
			close(os);
			close(bos);
		}
		return rv;
	}

	@SuppressWarnings("unchecked")
	public static Object deserialize(byte[] in) {
		return deserialize(in, Object.class);
	}

	@SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] in, Class<T>... requiredType) {
		Object rv = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				rv = is.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("deserialize error");
		} finally {
			close(is);
			close(bis);
		}
		return (T) rv;
	}

	private static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
				LOG.error("close stream error");
			}
		}
	}
}

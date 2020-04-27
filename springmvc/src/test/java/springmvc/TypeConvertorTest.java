package springmvc;

import com.summer.tech.springmvc.entity.FormatterModel;
import com.summer.tech.springmvc.entity.PhoneNumberModel;
import com.summer.tech.springmvc.utils.PhoneNumberFormatAnnotationFormatterFactory;
import com.summer.tech.springmvc.utils.PhoneNumberFormatter;
import com.summer.tech.springmvc.utils.StringToPhoneNumberConverter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.number.CurrencyFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class TypeConvertorTest {

    @Test
    public void testStringToPhoneNumberConvert() {
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        defaultConversionService.addConverter(new StringToPhoneNumberConverter());

        String phone = "010-12345678";
        PhoneNumberModel phoneNumberModel = defaultConversionService.convert(phone, PhoneNumberModel.class);

        Assert.assertEquals("010",phoneNumberModel.getAreaCode());
    }

    @Test
    public void testOtherConvert() {
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        Boolean bool = defaultConversionService.convert("1", Boolean.class);
        System.out.println(bool);
    }

    @Test
    public void testFormatter() throws ParseException {
        CurrencyFormatter currencyFormatter = new CurrencyFormatter();
        currencyFormatter.setFractionDigits(2);
        currencyFormatter.setRoundingMode(RoundingMode.CEILING);
        BigDecimal amount = currencyFormatter.parse("$2323.3232", Locale.US);
        System.out.println(amount);

        System.out.println(currencyFormatter.print(new BigDecimal("2323.23"),Locale.US));
        System.out.println(currencyFormatter.print(new BigDecimal("2323.23"),Locale.CHINA));
        System.out.println(currencyFormatter.print(new BigDecimal("2323.23"),Locale.JAPAN));
    }

    @Test
    public void testCustomFormatter(){
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addFormatter(new PhoneNumberFormatter());

        PhoneNumberModel model = new PhoneNumberModel("010","12345678");
        System.out.println(conversionService.convert(model,String.class));

        PhoneNumberModel phoneNumberModel = conversionService.convert("010-12345678", PhoneNumberModel.class);
        System.out.println(phoneNumberModel.getAreaCode());
    }

    @Test
    public void testAnnotation() throws NoSuchFieldException {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        //准备测试模型对象
        FormatterModel model = new FormatterModel();
        model.setTotalCount(10000);
        model.setDiscount(0.51);
        model.setSumMoney(10000.13);
        model.setRegisterDate(new Date(2012-1900, 4, 1));
        model.setOrderDate(new Date(2012-1900, 4, 1, 20, 18, 18));

        //获取类型信息
        TypeDescriptor descriptor =
                new TypeDescriptor(FormatterModel.class.getDeclaredField("totalCount"));
        TypeDescriptor stringDescriptor = TypeDescriptor.valueOf(String.class);

        Assert.assertEquals("10,000", conversionService.convert(model.getTotalCount(), descriptor, stringDescriptor));
        Assert.assertEquals(model.getTotalCount(), conversionService.convert("10,000", stringDescriptor, descriptor));

        descriptor = new TypeDescriptor(FormatterModel.class.getDeclaredField("registerDate"));
        Assert.assertEquals("2012-05-01", conversionService.convert(model.getRegisterDate(), descriptor, stringDescriptor));
        Assert.assertEquals(model.getRegisterDate(), conversionService.convert("2012-05-01", stringDescriptor, descriptor));

        descriptor = new TypeDescriptor(FormatterModel.class.getDeclaredField("orderDate"));
        Assert.assertEquals("2012-05-01 20:18:18", conversionService.convert(model.getOrderDate(), descriptor, stringDescriptor));
        Assert.assertEquals(model.getOrderDate(), conversionService.convert("2012-05-01 20:18:18", stringDescriptor, descriptor));
    }

    @Test
    public void testCustomAnnotation() throws NoSuchFieldException {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addFormatterForFieldAnnotation(new PhoneNumberFormatAnnotationFormatterFactory());

        FormatterModel formatterModel = new FormatterModel();
        TypeDescriptor phoneType = new TypeDescriptor(FormatterModel.class.getDeclaredField("phoneNumberModel"));
        TypeDescriptor stringType = TypeDescriptor.valueOf(String.class);

        PhoneNumberModel model = (PhoneNumberModel)conversionService.convert("010-12345678", stringType, phoneType);
        formatterModel.setPhoneNumberModel(model);
        System.out.println(model.getPhoneNumber());

        String phoneNumber = (String)conversionService.convert(formatterModel.getPhoneNumberModel(), phoneType, stringType);
        System.out.println(phoneNumber);

    }
}

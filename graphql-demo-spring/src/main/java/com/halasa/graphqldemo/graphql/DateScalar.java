package com.halasa.graphqldemo.graphql;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateScalar {

    public static final GraphQLScalarType DATE = GraphQLScalarType.newScalar()
            .name("Date")
            .description("A custom scalar that handles dates")
            .coercing(new Coercing<>() {
                @Override
                public Object serialize(Object dataFetcherResult) {
                    return serializeDate(dataFetcherResult);
                }

                @Override
                public Object parseValue(Object input) {
                    return parseDateFromVariable(input);
                }

                @Override
                public Object parseLiteral(Object input) {
                    return parseDateFromAstLiteral(input);
                }
            })
            .build();

    private static Object serializeDate(Object dataFetcherResult) {
        if (dataFetcherResult instanceof LocalDate) {
            return DateTimeFormatter.ISO_LOCAL_DATE.format((LocalDate) dataFetcherResult);
        }
        throw new CoercingSerializeException("Unable to serialize " + dataFetcherResult + " as a date");
    }

    private static Object parseDateFromVariable(Object input) {
        if (input instanceof String) {
            return (LocalDate) DateTimeFormatter.ISO_LOCAL_DATE.parse((String) input);
        }
        throw new CoercingParseValueException("Unable to parse variable value " + input + " as a date");
    }

    private static Object parseDateFromAstLiteral(Object input) {
        if (input instanceof StringValue) {
            String possibleDateValue = ((StringValue) input).getValue();
            return parseDateFromVariable(possibleDateValue);
        }
        throw new CoercingParseLiteralException("Value is not a date: '" + String.valueOf(input) + "'");
    }
}

// TODO remove
//
//package org.leibnizcenter.cfg.earleyparser.event;
//
//
//
//import org.leibnizcenter.cfg.earleyparser.parse.ParserOption;
//
///**
// * An event generated by an option being set on an {@link EarleyParser Earley
// * parser}.
// *
// // // */
//@SuppressWarnings("unused")
//public class ParserOptionEvent extends ParserEvent {
//    private static final long serialVersionUID = 1L;
//
//    public ParserOption option;
//    public Boolean value;
//
//    /**
//     * Creates a new event signaling that the specified option was set on
//     * the specified parser with the given value.
//     *
//     * @param earleyParser The parser on which the option was set.
//     * @param option       The option that was set.
//     * @param value        The value assigned to <code>option</code> for this parser.
//     */
//    public ParserOptionEvent(EarleyParser earleyParser,
//                             ParserOption option, Boolean value) {
//        super(earleyParser);
//        this.option = option;
//        this.value = value;
//    }
//
//    /**
//     * Gets the option that was set.
//     */
//    public ParserOption getOption() {
//        return option;
//    }
//
//    /**
//     * Gets the value that was set for the option.
//     */
//    public Boolean getValue() {
//        return value;
//    }
//
//}

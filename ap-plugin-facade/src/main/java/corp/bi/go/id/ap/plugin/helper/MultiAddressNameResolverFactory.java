package corp.bi.go.id.ap.plugin.helper;

import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultiAddressNameResolverFactory extends NameResolver.Factory {

    private final List<EquivalentAddressGroup> addresses;

    /**
     * Auto generate address for load balancing
     *
     * @param addresses {@value = "localhost:9090;localhost:9091"} divided by ';'
     * @return
     */
    public MultiAddressNameResolverFactory(String addresses) {
        List<SocketAddress> socketAddresses = generateMultiAddress(addresses);
        this.addresses = socketAddresses.stream().map(EquivalentAddressGroup::new).collect(Collectors.toList());
    }

    @Override
    public NameResolver newNameResolver(URI uri, NameResolver.Args args) {
        return new NameResolver() {
            @Override
            public String getServiceAuthority() {
                return "fakeAuthority";
            }

            @Override
            public void start(Listener2 listener) {
                listener.onResult(ResolutionResult.newBuilder().setAddresses(addresses).setAttributes(Attributes.EMPTY).build());
            }

            @Override
            public void shutdown() {

            }
        };
    }

    @Override
    public String getDefaultScheme() {
        return "multiaddress";
    }

    /**
     * Generate address for load balancing
     *
     * @param addresses {@value = "localhost:9090;localhost:9091"}
     * @return
     */
    private List<SocketAddress> generateMultiAddress(String addresses) {
        List<SocketAddress> multiAddress = new ArrayList<>();
        for (String address : addresses.split(";")) {
            String[] a = address.split(":");
            multiAddress.add(new InetSocketAddress(a[0], Integer.parseInt(a[1])));
        }
        return multiAddress;
    }
}